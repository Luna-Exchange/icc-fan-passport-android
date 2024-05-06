package com.insomnia.fanpassport

import android.webkit.JavascriptInterface

class WebAppInterface(private val onJavScriptInterface: OnJavScriptInterface) {

    @JavascriptInterface
    fun receiveEvent(data: String): Boolean {
        onJavScriptInterface.onNavigateBack()
        return true
    }
}

interface OnJavScriptInterface{
    fun onNavigateBack()
}