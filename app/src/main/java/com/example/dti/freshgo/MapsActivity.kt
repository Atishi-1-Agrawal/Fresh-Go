package com.example.dti.freshgo

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi

class MapsActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        supportActionBar?.hide()

        val webView = findViewById<WebView>(R.id.webView)

        webViewSetup(webView)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun webViewSetup(webView: WebView){
        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        webView.webViewClient = WebViewClient()

        webView.apply {
            settings.javaScriptEnabled = true
            settings.safeBrowsingEnabled = true
            loadUrl("https://www.google.com/maps/search/public+washrooms+near+me/@28.4387897,77.5019409,12z/data=!3m1!4b1")
        }
    }
}