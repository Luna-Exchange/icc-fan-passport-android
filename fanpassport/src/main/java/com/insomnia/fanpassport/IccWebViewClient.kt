package com.insomnia.fanpassport

import android.graphics.Bitmap
import android.webkit.WebView
import android.webkit.WebViewClient

class IccWebViewClient(private val onIccWebViewInterface: IccWebViewInterface) : WebViewClient() {
    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        onIccWebViewInterface.onPageStarted()
    }

    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        if (url.contains(MINT_BASE_CREATE_WALLET)) {
            onIccWebViewInterface.shouldOverrideCreateWallet()
        } else if (url.contains(MINT_BASE_SIGN_TRANSACTION)) {
            onIccWebViewInterface.shouldOverrideSignTransaction(url)
        } else if (url.contains(CLICK_UP_LINK)) {
            onIccWebViewInterface.shouldOverrideCustomersFeedBackForm()
        }
        return true
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        onIccWebViewInterface.onPageFinished()
    }
}


interface IccWebViewInterface {
    fun onPageStarted()

    fun onPageFinished()

    fun shouldOverrideCreateWallet()

    fun shouldOverrideSignTransaction(url: String)

    fun shouldOverrideCustomersFeedBackForm()
}