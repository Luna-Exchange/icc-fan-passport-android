package com.insomnia.fanpassport

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.ConsoleMessage
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import java.net.URL


const val PARAM_EXTRA = "USER_DETAILS"
const val MINT_BASE_CREATE_WALLET = "connect"
const val MINT_BASE_SIGN_TRANSACTION = "sign"


class IccFanPassportActivity : AppCompatActivity(),
    OnJavScriptInterface, IccWebViewInterface {

    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar
    private lateinit var background: ConstraintLayout
    private var arguments: SdkParam? = null
    private val viewModel: FanPassportViewModel by viewModels()
    private lateinit var config: EnvConfig
    private lateinit var sharedPrefProvider: SharedPrefProvider

    private var shouldRefresh = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_icc_fan_passport)
        setupAndOpenFanPassport()
    }

    private fun setupAndOpenFanPassport() {
        setupViews()
        setupConfig()
        setupWebView()
        openFanPassport()
    }

    private fun setupConfig() {
        sharedPrefProvider = SharedPrefProvider(this)
        config = EnvConfig(arguments?.environment ?: Environment.DEVELOPMENT)
    }

    private fun setupViews() {
        progressBar = findViewById(R.id.progress_bar)
        background = findViewById(R.id.constraint_layout)
        arguments = intent.getParcelableExtra(PARAM_EXTRA)
    }

    private fun setupWebView() {
        webView = findViewById(R.id.web_view)
        val webSettings = webView.settings
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        webView.addJavascriptInterface(WebAppInterface(this), "Android")
        webView.webViewClient = IccWebViewClient(this)

        webView.webChromeClient = object : WebChromeClient() {
            override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
                Log.d(
                    "WebView", consoleMessage?.message() + " -- From line "
                            + consoleMessage?.lineNumber() + " of "
                            + consoleMessage?.sourceId()
                )
                return super.onConsoleMessage(consoleMessage)
            }
        }
    }

    private fun openFanPassport() {
        val token = sharedPrefProvider.getAccessToken()
        if (token.isEmpty()) {
            val url = config.iccUi
            loadUrlWithWebView(url)
        } else {
            encodeUser(arguments?.user)
            observeViewModel()
        }
    }

    private fun observeViewModel() {
        if (!shouldRefresh) return
        lifecycleScope.launch {
            viewModel.token
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect(::loadUrl)
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        val action = SharedPrefProvider(this).getState()
        if (action == SdkActions.WALLET.name) {
            handleCreateWalletDeepLink(intent)
        } else {
            shouldRefresh = true
            setupAndOpenFanPassport()
        }
    }

    private fun handleCreateWalletDeepLink(intent: Intent) {
        shouldRefresh = false
        val data = intent.data
        val accountId = data?.getQueryParameter("account_id") ?: ""
        val publicKey = data?.getQueryParameter("public_key") ?: ""
        if (accountId.isNotEmpty()) {
            webView.loadUrl("${config.iccUi}${EntryPoint.ONBOARDING.path}/connect-wallet?account_id=$accountId&public_key=${publicKey}")
        } else {
            webView.loadUrl("${config.iccUi}${EntryPoint.ONBOARDING.path}/claim-tier")
        }
    }

    private fun encodeUser(user: User?) {
        viewModel.encodeUser(user, config.iccApi)
    }

    private fun loadUrl(result: Result) {
        if (!shouldRefresh) return
        when (result) {
            is Result.Success -> {
                sharedPrefProvider.saveAccessToken(result.token)
                loadUrlBasedOnActions()
            }

            is Result.Failed -> {
                finish()
            }

            is Result.Default -> {}
        }
    }

    private fun loadUrlBasedOnActions() {
        var url = config.iccUi
        val token = sharedPrefProvider.getAccessToken()
        when (arguments!!.action) {
            SdkActions.SIGN_IN -> {
                url = "${config.iccUi}${arguments?.entryPoint}?passport_access=${token}"
            }

            SdkActions.LOG_OUT, SdkActions.DEFAULT -> {
                url = config.iccUi
            }

            else -> {}
        }
        loadUrlWithWebView(url)
    }

    private fun loadUrlWithWebView(url: String) {
        webView.loadUrl(url)
    }

    private fun launchInAppBrowser(url: String) {
        val builder = CustomTabsIntent.Builder()
        builder.setShowTitle(true)

        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(url))
    }


    override fun onNavigateBack() {
        finish()
    }

    override fun onAuthenticateWithIcc() {
        SharedPrefProvider(this).saveState(SdkActions.SIGN_IN)
        onAuthenticate?.signIn()
    }

    override fun onResume() {
        super.onResume()
        if (arguments?.user != null) {
            encodeUser(arguments?.user)
            observeViewModel()
        }
    }


    override fun onDeepLinkToFantasy() {
        val deepLinkUri = Uri.parse(config.fantasyUri)
        val intent = Intent(Intent.ACTION_VIEW, deepLinkUri)
        startActivity(intent)
    }

    override fun onDeepLinkToPrediction() {
        val deepLinkUri = Uri.parse(config.predictionUri)
        val intent = Intent(Intent.ACTION_VIEW, deepLinkUri)
        startActivity(intent)
    }

    override fun onLogOut() {
        finish()
    }

    override fun onPageStarted() {
        progressBar.visibility = View.VISIBLE
    }

    override fun OnPageFinished() {

        webView.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        background.visibility = View.GONE
        loadUrlWithWebView(
            """
    javascript:(function() {
        window.parent.addEventListener('navigate-to-icc', function(event) {
            Android.receiveEvent(JSON.stringify(event));
        });
        window.parent.addEventListener('sign-in-with-icc', function(event) {
            Android.receiveSignInEvent(JSON.stringify(event));
        });
        window.parent.addEventListener('go-to-fantasy', function(event) {
            Android.receiveFantasyEvent(JSON.stringify(event));
        });
        window.parent.addEventListener('go-to-prediction', function(event) {
            Android.receivePredictionEvent(JSON.stringify(event));
        });
        window.parent.addEventListener('fan-passport-sign-out', function(event) {
            Android.receivePredictionEvent(JSON.stringify(event));
        });
    })()
    """
        )

    }

    override fun shouldOverrideCreateWallet() {
        SharedPrefProvider(this).saveState(SdkActions.WALLET)
        val connectBrowserUrl =
            "${config.mintBaseUrl}&success_url=${config.scheme}://mintbase.xyz"
        launchInAppBrowser(connectBrowserUrl)
    }

    override fun shouldOverrideSignTransaction(url: String) {
        SharedPrefProvider(this).saveState(SdkActions.WALLET)
        val connectBrowserUrl =
            "${removeCallbackUrl(url)}&callback_url=${config.scheme}://mintbase.xyz"
        launchInAppBrowser(connectBrowserUrl)
    }


    companion object {
        private var onAuthenticate: OnAuthenticate? = null

        fun launch(
            context: Activity,
            user: User? = null,
            entryPoint: String = EntryPoint.ONBOARDING.path,
            environment: Environment = Environment.DEVELOPMENT,
            onAuthenticate: OnAuthenticate? = null
        ) {
            val sdkParam = if (user != null) SdkParam(user).copy(
                action = SdkActions.SIGN_IN,
                entryPoint = entryPoint,
                environment = environment
            ) else SdkParam(
                entryPoint = entryPoint,
                environment = environment
            )
            val token = sdkParam.user?.authToken.orEmpty()
            val sharedPrefProvider = SharedPrefProvider(context)
            sharedPrefProvider.saveAccessToken(token)
            this.onAuthenticate = onAuthenticate
            val intent = Intent(context, IccFanPassportActivity::class.java)
            intent.putExtra(PARAM_EXTRA, sdkParam)
            context.startActivity(intent)
        }


        fun logOut(context: Activity) {
            SharedPrefProvider(context).apply {
                saveState(SdkActions.LOG_OUT)
                saveAccessToken("")
            }
        }
    }

    private fun removeCallbackUrl(originalUrl: String): String {
        val url = URL(originalUrl)
        val queryParams = url.query.split("&").toMutableList()
        val filteredParams = queryParams.filter { !it.startsWith("callback_url=") }
        val newQuery = filteredParams.joinToString("&")
        return "${url.protocol}://${url.host}${url.path}?$newQuery"
    }

}


interface OnAuthenticate {
    fun signIn()

    fun onNavigateBack()

}

enum class SdkActions {
    DEFAULT,
    SIGN_IN,
    WALLET,
    LOG_OUT
}