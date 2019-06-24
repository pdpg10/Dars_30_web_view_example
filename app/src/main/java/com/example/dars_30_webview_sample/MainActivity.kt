package com.example.dars_30_webview_sample

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


private const val BASE_PREFIX = "https://"

class MainActivity : AppCompatActivity(),
    TextView.OnEditorActionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        et_url.setOnEditorActionListener(this)
        setUpWebView()
        ib_left.setOnClickListener { onBackPressed() }
        ib_right.setOnClickListener { onGoForward() }
    }

    private fun onGoForward() {
        //todo homework_2 when onback pressed et_url update
        if (web_view.canGoForward()) web_view.goForward()

    }

    private fun setUpWebView() {
//        web_view.webChromeClient
        //todo howe_work_1 determine progress with webChromeClient
        web_view.webViewClient = MyWebViewClient()
    }

    override fun onEditorAction(
        v: TextView?,
        actionId: Int,
        event: KeyEvent?
    ): Boolean {
        return if (actionId == EditorInfo.IME_ACTION_DONE) {
            loadNewUrl()
            true
        } else false
    }

    private fun loadNewUrl() {
        val baseUrl = et_url.text.toString()
        val url = "$BASE_PREFIX$baseUrl"
        web_view.loadUrl(url)
        et_url.hideKeyboard()
    }

    override fun onBackPressed() {
        if (web_view.canGoBack()) web_view.goBack()
        else super.onBackPressed()
    }

    inner class MyWebViewClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            Log.d("MyWebViewClient", request.toString())
            updateUrlView(request?.url.toString())
            return super.shouldOverrideUrlLoading(view, request)
        }
    }

    private fun updateUrlView(url: String) {
        et_url.setText(url)
    }
}
