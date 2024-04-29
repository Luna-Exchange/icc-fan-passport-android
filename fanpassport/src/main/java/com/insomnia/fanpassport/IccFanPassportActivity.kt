package com.insomnia.fanpassport

import android.content.Context
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
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch


const val USER_EXTRA = "USER_DETAILS"
const val PASSPORT_URL = "https://icc-fan-passport-staging.vercel.app/?passport_access="


class IccFanPassportActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar
    private val viewModel: FanPassportViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_icc_fan_passport)
        webView = findViewById(R.id.web_view)
        progressBar = findViewById(R.id.progress_bar)
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
                progressBar.visibility = View.GONE
                webView.loadUrl(
                    "javascript:(function() {" +
                            "window.parent.addEventListener ('click', function(event) {" +
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
        val user = intent.getParcelableExtra<User>(USER_EXTRA)
        viewModel.encodeUser(user)
    }

    private fun loadUrl(result: Result) {
        when (result) {
            is Result.Success -> {
                val url = PASSPORT_URL + result.token
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

    class Builder(private val context: Context) {
        private var accessToken: String = ""
        private var name: String = ""
        private var email: String = ""

        fun accessToken(accessToken: String) = apply { this.accessToken = accessToken }
        fun name(name: String) = apply { this.name = name }
        fun email(email: String) = apply { this.email = email }

        fun build() {
            val intent = Intent(context, IccFanPassportActivity::class.java)
            val user =
                User(authToken = accessToken, name = name, email = email)
            intent.putExtra(USER_EXTRA, user)
            context.startActivity(intent)
        }
    }
}