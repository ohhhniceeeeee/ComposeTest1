package com.example.app.ui.components

import android.graphics.Bitmap
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun WebViewCompose(state: WebViewState) {

    var webView by remember {
        mutableStateOf<WebView?>(null)
    }

    LaunchedEffect(key1 = webView, key2 = state) {
        with(state) {
            //订阅流
            webView?.handleEvent()
        }
    }

    AndroidView(factory = { context ->
        WebView(context).apply {

            webChromeClient = object : WebChromeClient() {
                override fun onReceivedTitle(view: WebView?, title: String?) {
                    super.onReceivedTitle(view, title)
                    state.pageTitle = title
                }
            }
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    state.pageTitle = null
                }
            }



            with(settings) {
                javaScriptEnabled = true
            }
        }.also { webView = it }

    }) { view ->
//        view.loadUrl("https://baidu.com")
//        view.loadDataWithBaseURL("", "<h1>Header</h1>", null, "utf-8", null)

        when (val content = state.content) {
            is WebContent.Url -> {
                val url = content.url
                if (url.isNotEmpty() && url != view.url) {
                    view.loadUrl(url)
                }
            }

            is WebContent.Data -> {
                view.loadDataWithBaseURL(content.baseUrl, content.data, null, "utf-8", null)
            }
        }
    }
}

sealed class WebContent() {
    data class Url(val url: String) : WebContent()
    data class Data(val data: String, val baseUrl: String? = null) : WebContent()
}

class WebViewState(private val coroutineScope: CoroutineScope, webContent: WebContent) {
    var content by mutableStateOf(webContent)

    //TODO WebView页面标题部分/调用范围问题
    var pageTitle: String? by mutableStateOf(null)
        internal set

    enum class EventType {
        EVALUATE_JAVASCRIPT//执行 JS 方法
    }

    //共享流的数据类型
    class Event(val type: EventType, val args: String, val callback: ((String) -> Unit)?)

    //共享流
    private val events: MutableSharedFlow<Event> = MutableSharedFlow()

    internal suspend fun WebView.handleEvent(): Unit = withContext(Dispatchers.Main) {
        events.collect { event ->
            when (event.type) {
                EventType.EVALUATE_JAVASCRIPT -> evaluateJavascript(event.args, event.callback)
            }
        }
    }

    //执行JS方法
    fun evaluateJavascript(script: String, resultCallback: ((String) -> Unit)? = {}) {
        val event = Event(EventType.EVALUATE_JAVASCRIPT, script, callback = resultCallback)
        coroutineScope.launch {
            events.emit(event)
        }
    }
}

@Composable
fun rememberWebViewState(coroutineScope: CoroutineScope = rememberCoroutineScope(), url: String) =
    remember(key1 = url) {
        WebViewState(coroutineScope, WebContent.Url(url))
    }

@Composable
fun rememberWebViewState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    data: String,
    baseUrl: String? = null
) =
    remember(key1 = data, key2 = baseUrl) {
        WebViewState(coroutineScope, WebContent.Data(data, baseUrl))
    }