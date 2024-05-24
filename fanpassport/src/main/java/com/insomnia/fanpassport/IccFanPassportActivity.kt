package com.insomnia.fanpassport

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.ConsoleMessage
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch


const val PARAM_EXTRA = "USER_DETAILS"
const val MINT_BASE_HOST = "mintbase"

class IccFanPassportActivity : AppCompatActivity(),
    OnJavScriptInterface {

    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar
    private lateinit var background: ConstraintLayout
    private var arguments: ActivityParam? = null
    private val viewModel: FanPassportViewModel by viewModels()
    private lateinit var config: EnvConfig


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_icc_fan_passport)
        webView = findViewById(R.id.web_view)
        progressBar = findViewById(R.id.progress_bar)
        background = findViewById(R.id.constraint_layout)
        arguments = intent.getParcelableExtra(PARAM_EXTRA)
        config = EnvConfig(arguments?.environment ?: Environment.DEVELOPMENT)
        openFanPassport()
        val webSettings = webView.settings
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        webView.addJavascriptInterface(WebAppInterface(this), "Android")

        webView.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progressBar.visibility = View.VISIBLE

            }

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                if (url.contains(MINT_BASE_HOST)) {
                    launchInAppBrowser("${config.mintBaseUrl}/?success_url=${config.scheme}://mintbase.xyz")
                }
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                webView.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
                background.visibility = View.GONE
                loadUrlWithWebView(
                    "javascript:(function() {" +
                            "window.parent.addEventListener ('navigate-to-icc', function(event) {" +
                            " Android.receiveEvent(JSON.stringify(event));});" +
                            "})()"
                )
                loadUrlWithWebView(
                    "javascript:(function() {" +
                            "window.parent.addEventListener ('sign-in-with-icc', function(event) {" +
                            " Android.receiveSignInEvent(JSON.stringify(event));});" +
                            "})()"
                )
                loadUrlWithWebView(
                    "javascript:(function() {" +
                            "window.parent.addEventListener ('go-to-fantasy', function(event) {" +
                            " Android.receiveFantasyEvent(JSON.stringify(event));});" +
                            "})()"
                )
                loadUrlWithWebView(
                    "javascript:(function() {" +
                            "window.parent.addEventListener ('go-to-prediction', function(event) {" +
                            " Android.receivePredictionEvent(JSON.stringify(event));});" +
                            "})()"
                )
            }
        }

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
        if (arguments?.user?.authToken.isNullOrEmpty()) {
            val url = config.iccUi
            loadUrlWithWebView(url)
        } else {
            encodeUser()
            observeViewModel()
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.token
                .flowWithLifecycle(lifecycle)
                .collect(::loadUrl)
        }
    }

    private fun encodeUser() {
        arguments = intent.getParcelableExtra(PARAM_EXTRA)
        viewModel.encodeUser(arguments?.user, config.iccApi)
    }

    private fun isDeepLinkFromWallet(token: String): String {
        return "${config.iccUi}${EntryPoint.ONBOARDING.path}/connect-wallet?passport_access=${token}&account_id=${arguments?.accountId}&public_key=${arguments?.publicKey}"
    }

    private fun loadUrl(result: Result) {

        when (result) {
            is Result.Success -> {
                val url =
                    if (!arguments?.accountId.isNullOrEmpty()) {
                        isDeepLinkFromWallet(result.token)
                    } else {
                        "${config.iccUi}${arguments?.path}/?passport_access=${result.token}"
                    }
                loadUrlWithWebView(url)
            }

            is Result.Failed -> {}
        }
    }

    private fun loadUrlWithWebView(url: String) {
        webView.loadUrl(url)
    }

    fun launchInAppBrowser(url: String) {
        val builder = CustomTabsIntent.Builder()
        builder.setShowTitle(true)

        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(url))
    }


    override fun onNavigateBack() {
        finish()
    }

    override fun onAuthenticateWithIcc() {
        finish()
    }

    override fun onResume() {
        super.onResume()
        if (arguments?.user != null) {
            encodeUser()
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
        startActivity(intent)    }


     class Builder(private val activity: ComponentActivity) {
        private var accessToken: String = ""
        private var name: String = ""
        private var email: String = ""
        private var publicKey: String = ""
        private var accountId: String = ""
        private var entryPoint: String = EntryPoint.DEFAULT.path
        private var environment: Environment = Environment.DEVELOPMENT
        private lateinit var onNavigateBack: () -> Unit

        private val resultLauncher = activity.registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { onNavigateBack.invoke() }

        fun accessToken(accessToken: String) = apply { this.accessToken = accessToken }
        fun name(name: String) = apply { this.name = name }
        fun email(email: String) = apply { this.email = email }
        fun publicKey(email: String) = apply { this.publicKey = email }
        fun accountId(email: String) = apply { this.accountId = email }
        fun environment(environment: Environment) = apply { this.environment = environment }
        fun entryPoint(entryPoint: String) = apply { this.entryPoint = entryPoint }

        fun onNavigateBack(onNavigateBack: () -> Unit) = apply { this.onNavigateBack = onNavigateBack }


        fun build() {
            val intent = Intent(activity, IccFanPassportActivity::class.java)
            val user = User(authToken = accessToken, name = name, email = email)
            val param = ActivityParam(user, entryPoint, publicKey, accountId, environment)
            intent.putExtra(PARAM_EXTRA, param)
            resultLauncher.launch(intent)
        }

    }

}