package com.insomnia.fanpassport

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.webkit.ConsoleMessage
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.insomnia.passport.WebAppInterface


const val CURRENT_URL = "currentUrl"
const val SHARED_PREF = "MyPrefs"

class IccFanPassportActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private var currentUrl: String? = null
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_icc_fan_passport)

        sharedPreferences = getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        webView = findViewById(R.id.web_view)
        val webSettings = webView.settings
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        webView.addJavascriptInterface(WebAppInterface(this), "Android")
        currentUrl = sharedPreferences.getString(CURRENT_URL, "")
        webView.loadUrl("https://starter.mintbase.xyz")

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
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

    override fun onPause() {
        super.onPause()
        val url = webView.url
        editor.putString(CURRENT_URL, url).apply()
    }

    companion object  {
        fun start(context: Context) {
            val intent = Intent(context, IccFanPassportActivity::class.java)
            context.startActivity(intent)
        }
    }
}