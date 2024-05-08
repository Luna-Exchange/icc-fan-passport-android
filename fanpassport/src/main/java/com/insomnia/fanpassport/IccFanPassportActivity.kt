package com.insomnia.fanpassport

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.ConsoleMessage
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


const val PARAM_EXTRA = "USER_DETAILS"

class IccFanPassportActivity : AppCompatActivity(), OnJavScriptInterface {

    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar
    private lateinit var background: ConstraintLayout
    private var arguments: ActivityParam? = null
    private val viewModel: FanPassportViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_icc_fan_passport)
        webView = findViewById(R.id.web_view)
        progressBar = findViewById(R.id.progress_bar)
        background = findViewById(R.id.constraint_layout)
        encodeUser()
        observeViewModel()
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
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                webView.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
                background.visibility = View.GONE
                webView.loadUrl(
                    "javascript:(function() {" +
                            "window.parent.addEventListener ('navigate-to-icc', function(event) {" +
                            " Android.receiveEvent(JSON.stringify(event));});" +
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
                );
                return super.onConsoleMessage(consoleMessage)
            }
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
        viewModel.encodeUser(arguments?.user)
    }

    private fun loadUrl(result: Result) {
        when (result) {
            is Result.Success -> {
                val url = "https://icc-fan-passport-staging.vercel.app${arguments?.path}?passport_access=${result.token}"
                webView.loadUrl(url)
            }

            is Result.Failed -> {
                Toast.makeText(
                    this,
                    "Could not complete Auth Flow ${result.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    class Builder(private val activity: ComponentActivity) {
        private var accessToken: String = ""
        private var name: String = ""
        private var email: String = ""
        private var entryPoint: EntryPoint = EntryPoint.DEFAULT
        private lateinit var onNavigateBack: () -> Unit

        private val resultLauncher = activity.registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { onNavigateBack.invoke() }

        fun accessToken(accessToken: String) = apply { this.accessToken = accessToken }
        fun name(name: String) = apply { this.name = name }
        fun email(email: String) = apply { this.email = email }
        fun entryPoint(entryPoint: EntryPoint) = apply { this.entryPoint = entryPoint }

        fun onNavigateBack(onNavigateBack: () -> Unit) =
            apply { this.onNavigateBack = onNavigateBack }


        fun build() {
            val intent = Intent(activity, IccFanPassportActivity::class.java)
            val user = User(authToken = accessToken, name = name, email = email)
            val param = ActivityParam(user, entryPoint.path)
            intent.putExtra(PARAM_EXTRA, param)
            resultLauncher.launch(intent)
        }
    }

    override fun onNavigateBack() {
        finish()
    }

}