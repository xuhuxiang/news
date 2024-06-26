package com.example.anew.ui.Screens

import android.annotation.SuppressLint

import android.webkit.WebView

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon

import androidx.compose.material3.Slider

import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.anew.ui.ViewModel.NewsState
import com.example.anew.ui.ViewModel.NewsViewModel
import com.example.anew.ui.component.ArticleItem
import com.example.anew.ui.component.WebView
import com.example.anew.ui.component.rememberWebViewState

import kotlinx.coroutines.launch


//Suspend function 'expand' should be called only from a coroutine or another suspend function
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ArticleDetailScreen(onBack:()->Unit,vm:NewsViewModel){
    when(val result=vm.newsData.value){
        is NewsState.Success -> {
            vm.content=result.news.result.list!!.get(vm.id).content
            vm.title=result.news.result.list!!.get(vm.id).title
        }
        is NewsState.Failure->{

        }
        is NewsState.Loading->{

        }

        else -> {}
    }
    val webViewState= rememberWebViewState(data=vm.header+"<h2>${vm.title}</h2>"+vm.content+vm.fotter)
    var fontScale by remember {
        mutableStateOf(1.0f)
    }

    var scaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope= rememberCoroutineScope()
    BottomSheetScaffold(
        scaffoldState=scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text("文章详情",modifier=Modifier.fillMaxWidth(), fontSize = 18.sp, textAlign = TextAlign.Center) },
                navigationIcon = {
                    Icon(imageVector = Icons.Default.NavigateBefore,contentDescription = null,
                        modifier=Modifier.clickable {
                            onBack()
                        })
                },
                actions = {
                    Icon(imageVector = Icons.Default.TextFields, contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                coroutineScope.launch {
                                    if (scaffoldState.bottomSheetState.isCollapsed)
                                        scaffoldState.bottomSheetState.expand()
                                    else scaffoldState.bottomSheetState.collapse()
                                }
                            }
                            .padding(8.dp))
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Gray.copy(alpha = 0.4f))
            )
        },
        sheetContent = {
            Column (modifier=Modifier.padding(8.dp)){
                Text(text="字体大小", fontSize = 16.sp)
                Slider(value = fontScale, onValueChange ={
                    fontScale=it
                    webViewState.evaluateJavascript("document.body.style.zoom=$it")
                } ,steps=3, valueRange = 0.75f..1.75f)
                Row(horizontalArrangement = Arrangement.SpaceBetween,
                    modifier=Modifier.fillMaxWidth()){
                    Text("较小", fontSize = 14.sp,color=Color(0xFF999999))  //0.75
                    Text("标准", fontSize = 14.sp,color=Color(0xFF999999))  //1.0
                    Text("普大", fontSize = 14.sp,color=Color(0xFF999999))  //1.25
                    Text("超大", fontSize = 14.sp,color=Color(0xFF999999))  //1.5
                    Text("特大", fontSize = 14.sp,color=Color(0xFF999999))  //1.75
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        },
        sheetPeekHeight = 0.dp
    ){
        Box(modifier = Modifier.padding(it)) {
            WebView(state = webViewState)
        }
    }
}