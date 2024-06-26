package com.example.anew.ui.component

import android.graphics.Bitmap
import android.provider.ContactsContract
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.w3c.dom.Text

@Composable
fun WebView(state:WebViewState) {

    var webView by remember {
        mutableStateOf<WebView?>(null)
    }
    //webview变化或state变化时重新订阅流数据
    LaunchedEffect(webView,state){
        with(state){
            webView?.handleEvent()
        }
    }
    AndroidView(factory = {
            ctx->
        WebView(ctx).apply{
            with(settings){
                javaScriptEnabled=true
            }
            webChromeClient=object:WebChromeClient(){
                override fun onReceivedTitle(view: WebView?, title: String?) {
                    super.onReceivedTitle(view, title)
                    state.pageTitle=title
                }
            }

            webViewClient= object :WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    state.pageTitle=null
                }
            }
        }.also { webView = it}

    }){
        view->
        when(val content = state.content){
            is WebContent.Url->{
                val url = content.url

                if(url.isNotEmpty() && url != view.url){
                    view.loadUrl(content.url)
                }
            }
            is WebContent.Data->{
                view.loadDataWithBaseURL(
                    content.baseUrl,
                    content.data,
                    null,
                    "utf-8",
                    null
                )
            }
            else -> {}
        }
        //view.loadDataWithBaseURL("","<h1>Header</h1>",null,"utf-8",null)
        //view.loadUrl("https://sports.sina.cn/seriea/inter/2023-12-24/detail-imzzaine4852048.d.html?vt=4&pos=108")
    }

}

sealed class WebContent(){
    data class Url(val url:String):WebContent()
    data class Data(val data:String,val baseUrl: String?=null):WebContent()
}
class WebViewState(private val coroutineScope: CoroutineScope,webContent:WebContent){
    //网页内容url或data（html内容）
    var content by mutableStateOf(webContent)
    //TODO遗留问题
    var pageTitle:String? by mutableStateOf(null)
    //事件类型
    enum class EventType{
        EVALUATE_JAVASCRIPT  //执行JS方法
    }
    //共享流的数据类型
    class Event(val type:EventType,val args:String,val callback:((String)->Unit)?)
    //共享流
    private val events:MutableSharedFlow<Event> = MutableSharedFlow()
    suspend fun WebView.handleEvent() :Unit= withContext(Dispatchers.Main){
        events.collect { event ->
            when(event.type){
                EventType.EVALUATE_JAVASCRIPT -> evaluateJavascript(event.args,event.callback)
            }
        }
    }
    //执行Js方法
    fun evaluateJavascript(script:String,resultCallBack:((String)->Unit)?= {}){
        val event = Event(EventType.EVALUATE_JAVASCRIPT,script,resultCallBack)
        coroutineScope.launch {
            events.emit(event)  //推送流
        }
    }
}

@Composable
fun rememberWebViewState(coroutineScope: CoroutineScope= rememberCoroutineScope(),data:String,baseUrl:String?=null) = remember(key1 = data,key2 =baseUrl)
{
    WebViewState(coroutineScope,WebContent.Data(data,baseUrl))
}
@Composable
fun rememberWebViewState(coroutineScope: CoroutineScope= rememberCoroutineScope(), url:String) = remember(key1 = url)
{
    WebViewState(coroutineScope,WebContent.Url(url))
}