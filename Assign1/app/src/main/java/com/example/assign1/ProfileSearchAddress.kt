package com.example.assign1

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.webkit.*
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_profile_search_address.*

class ProfileSearchAddress : AppCompatActivity() {

    companion object{
        const val ADDRESS_REQUEST_CODE = 2928
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_search_address)

        val webView = findViewById<WebView>(R.id.searchAddWebView)
        webView.settings.javaScriptEnabled = true

        webView.addJavascriptInterface(KaKaoJavaScriptInterface(), "Android")
        webView.webViewClient = object : WebViewClient(){

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                Log.v("WebView Log","WebView Log onPageStarted")
            }

            override fun onPageFinished(view: WebView, url: String){
//                webView.loadUrl("javascript:sample2_execDaumPostcode")
//                webView.loadUrl("http://www.profileaddresssearch.ga/daum.html")

                Log.v("WebView Log","WebView log onPageFinished")
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                Log.v("WebView Log","WebView Log onRecevedError")
                Toast.makeText(App.context(),"페이지와 연결이 되지 않습니다",Toast.LENGTH_LONG).show()
            }
        }

//        webView.loadUrl("src/main/assets/kakao.html")
        webView.loadUrl("http://www.profileaddresssearch.ga/daum.html")


    }

    inner class KaKaoJavaScriptInterface{

        @JavascriptInterface
        @SuppressWarnings("unused")
        fun processDATA(data: String?){
            Intent().apply {
                putExtra("data",data)
                setResult(RESULT_OK, this)
            }
            finish()
        }
    }

}
