package com.insomnia.fanpassport

import android.webkit.JavascriptInterface

class WebAppInterface(private val onJavScriptInterface: OnJavScriptInterface) {

    @JavascriptInterface
    fun receiveEvent(data: String): Boolean {
        onJavScriptInterface.onNavigateBack()
        return true
    }

    @JavascriptInterface
    fun receiveSignInEvent(data: String): Boolean {
        onJavScriptInterface.onAuthenticateWithIcc()
        return true
    }

    @JavascriptInterface
    fun receiveFantasyEvent(data: String): Boolean {
        onJavScriptInterface.onDeepLinkToFantasy()
        return true
    }

    @JavascriptInterface
    fun receivePredictionEvent(data: String) : Boolean {
        onJavScriptInterface.onDeepLinkToPrediction()
        return true
    }
}

interface OnJavScriptInterface{
    fun onNavigateBack() {}

    fun onAuthenticateWithIcc()

    fun onDeepLinkToFantasy() {}

    fun onDeepLinkToPrediction() {}

}