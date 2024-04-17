package com.insomnia.fanpassport

import android.content.Context
import android.webkit.JavascriptInterface
import android.widget.Toast

class WebAppInterface(private val mContext: Context) {



    @JavascriptInterface
    fun receiveEvent(data: String): Boolean {
        Toast.makeText(mContext, "Clicked Now", Toast.LENGTH_SHORT).show()
        return true
    }

}