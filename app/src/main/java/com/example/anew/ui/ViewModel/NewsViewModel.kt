package com.example.anew.ui.ViewModel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anew.R
import com.example.anew.ui.Entity.NavigationItem
import com.example.anew.ui.repository.NewsRepository
import com.example.anew.ui.theme.NewTheme
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class NewsViewModel :ViewModel(){
    val newsData:MutableState<NewsState> = mutableStateOf(NewsState.Empty)
    val navigationItem = listOf(
        NavigationItem("首页", Icons.Filled.Home),
        NavigationItem("我的", Icons.Filled.Person)
    )
    var currentScreen by mutableStateOf(0)
        private set
    fun updateCurrentScreen(index:Int){
        currentScreen = index
    }
    var listItem = listOf(
        "头条", "新闻", "财经", "体育", "娱乐", "军事", "教育", "科技", "NBA", "股票", "星座", "女性", "健康", "育儿"
    )
    var selected by mutableStateOf(0)
    fun updateSelected(index:Int){
        selected=index
    }
    fun getNewsData(channel:String,appkey:String,num:Int){
        viewModelScope.launch {
            NewsRepository.getNews(channel,appkey,num)
                .onStart {
                    newsData.value=NewsState.Loading
                }
                .catch { e->
                    newsData.value=NewsState.Failure(e)
                }
                .collect{
                    response->
                    newsData.value=NewsState.Success(response)
                }
        }
    }
    var id:Int=0
    fun updateId(index:Int){
        id=index
    }
    var content by mutableStateOf("""
    """.trimIndent()
    )
    var num =40
    var title by mutableStateOf("")
    var header="""
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>${title}</title>
            <style>
                img{
                    max-width:100% !important;
                }
            </style>
        </head>
        <body>
    """.trimIndent()
    val fotter="""
        </body>
        </html>
    """.trimIndent()
    val pic = listOf<Int>(
        R.drawable.a,
        R.drawable.b,
        R.drawable.c,
        R.drawable.e,
        R.drawable.f,
        R.drawable.g,
        R.drawable.h,
        R.drawable.i,
        R.drawable.j,
        R.drawable.k,
        R.drawable.l,
        R.drawable.m
    )
    var currentPic  by mutableStateOf(R.drawable.logo)
    fun updatePic(pic :Int){
        currentPic = pic
    }


    var geyan by mutableStateOf("过好每一天")
    fun updateGeyan(name:String){
        geyan=name
    }
    var openState by  mutableStateOf(false)
    var showExitDialog by mutableStateOf(false)
    fun onShowExitDialogChange(op:Boolean){
        showExitDialog=op
    }
    var info: MutableState<MutableList<String>> = mutableStateOf(MutableList(7){""})

    var option by mutableStateOf(0)
    fun updateInfoAt(index: Int, newValue: String) {
        val currentList = info.value.toMutableList()
        currentList[index] = newValue
        info.value = currentList
    }
}