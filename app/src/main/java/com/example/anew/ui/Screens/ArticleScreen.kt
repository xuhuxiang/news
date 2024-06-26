package com.example.anew.ui.Screens


import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow

import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.runtime.LaunchedEffect

import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.anew.ui.ViewModel.NewsState
import com.example.anew.ui.ViewModel.NewsViewModel
import com.example.anew.ui.component.ArticleItem
import com.example.anew.ui.component.SwiperContent
import com.example.anew.ui.component.TopAppBar


@Composable
fun ArticleScreen(newsViewModel: NewsViewModel,onNavigateToArticle:()->Unit={}){

    Column(modifier=Modifier, verticalArrangement = Arrangement.Center) {
        TopAppBar {
            Text(text = "知天下新闻")
        }
        //分类标签
        LazyRow(Modifier.padding(0.dp, 8.dp), contentPadding = PaddingValues(12.dp, 0.dp)) {
            itemsIndexed(newsViewModel.listItem) { index, name ->
                Column(
                    Modifier
                        .padding(12.dp, 4.dp)
                        .width(IntrinsicSize.Max)) {
                    Text(name, fontSize = 15.sp,
                        modifier=Modifier.clickable {
                            newsViewModel.updateSelected(index)
                        },
                        color = if (index == newsViewModel.selected) Color(0xfffa9e51) else Color(0xffb4b4b4)
                    )
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp)
                            .height(2.dp)
                            .clip(RoundedCornerShape(1.dp))
                            .background(
                                if (index == newsViewModel.selected) Color(0xfffa9e51) else Color.Transparent
                            )
                    )
                }
            }
        }

        newsViewModel.getNewsData(newsViewModel.listItem[newsViewModel.selected],"b38ec8f8e5d4a5f2",newsViewModel.num)

        LazyColumn {
            //轮播图
            item {
                SwiperContent(vm = newsViewModel,onNavigateToArticle)
            }
            item{Spacer(modifier = Modifier.height(8.dp))}
            //新闻列表
            when(val result=newsViewModel.newsData.value){
                is NewsState.Success -> {
                    itemsIndexed(result.news.result.list!!){
                            index,article->
                        ArticleItem(article,modifier=Modifier.clickable {
                            newsViewModel.updateId(index)
                            onNavigateToArticle.invoke()
                        })
                    }
                }
                is NewsState.Failure->{

                }
                is NewsState.Loading->{

                }

                else -> {}
            }
        }
    }
}