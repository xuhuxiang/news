package com.example.anew.ui.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.anew.ui.MainApp

import com.example.anew.ui.ViewModel.NewsViewModel
import com.example.anew.ui.database.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun MainFrame(newsViewModel: NewsViewModel, onNavigateToArticle:()->Unit={},
              onNavigateToPic:()->Unit={},onNavigateToGe:()->Unit={},
              onNavigateToAbout:()->Unit={},onExit:()->Unit={},
              onNavigateToPersonInfo:()->Unit={}){
    val userInfo= UserDatabase.getInstance(MainApp.context).getInfoDao()
    CoroutineScope(Dispatchers.IO).launch{
        val info=userInfo.queryUserByName(newsViewModel.info.value[5])
        //if(info?.pic!=null)Screen
        newsViewModel.updatePic(info.pic)
        //if(info?.ge!=null)
        newsViewModel.updateGeyan(info.ge)
        for(i in 0..6){
            when(i){
                0->newsViewModel.info.value[i]=info.nicheng
                1->newsViewModel.info.value[i]=info.userReality
                2->newsViewModel.info.value[i]=info.sex
                3->newsViewModel.info.value[i]=info.region
                4->newsViewModel.info.value[i]=info.qianming
            }
        }

    }
    Scaffold (
        bottomBar = {
            BottomAppBar {
                newsViewModel.navigationItem.forEachIndexed { index, navigationItem ->
                    NavigationBarItem(
                        selected = index == newsViewModel.currentScreen,
                        onClick = {
                            newsViewModel.updateCurrentScreen(index)
                        },
                        icon = {
                            Icon(imageVector = navigationItem.icon, contentDescription = null)
                        },
                        label = {
                            Text(text = navigationItem.title)
                        }
                    )
                }
            }
        }
    ){
        Box(modifier=Modifier.padding(it)) {
            when (newsViewModel.currentScreen) {
                0 -> ArticleScreen(newsViewModel,onNavigateToArticle=onNavigateToArticle)
                1 -> MineScreen(newsViewModel,onNavigateToPic,onNavigateToGe,onNavigateToAbout,onExit,onNavigateToPersonInfo)
            }
        }
    }
}