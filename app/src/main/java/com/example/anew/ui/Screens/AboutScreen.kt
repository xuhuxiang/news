package com.example.anew.ui.Screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.anew.ui.Screens.ui.theme.NewTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AboutScreen(onBackToMine:()->Unit={}){
    Scaffold (topBar = {
        TopAppBar(
            title = { Text("关于APP",modifier=Modifier.fillMaxWidth(), fontSize = 18.sp, textAlign = TextAlign.Center) },
            navigationIcon = {
                Icon(imageVector = Icons.Default.NavigateBefore,contentDescription = null,
                    modifier=Modifier.clickable {
                        onBackToMine()
                    })
            }
        )
    }){
        Box(modifier=Modifier.padding(it)){
            LazyColumn(verticalArrangement = Arrangement.Center){
                item {
                    Text(text = """
                本项目旨在开发一款移动端在线新闻应用，
                提供给用户最新的新闻资讯和内容。提供一个简洁、直观的用户界面，使用户能够轻松浏览、阅读各类新闻。
                该应用使用极速数据的新闻API，确保新闻的实时性和多样性。
                功能特点:
                实时新闻更新：应用将实时更新最热门的新闻故事和头条，确保用户随时可以接触到最新资讯。
                新闻分类浏览：新闻将按类别进行整理，用户可以选择感兴趣的类别，如科技、娱乐、体育等进行浏览。
                该应用仅作为移动应用开发期末大作业使用，开发者徐虎祥。
            """.trimIndent(),fontSize=20.sp)
                }
            }
        }
    }

}