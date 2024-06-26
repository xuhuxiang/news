package com.example.anew.ui.Screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.material3.Button
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.anew.R
import com.example.anew.ui.Screens.ui.theme.NewTheme
import com.example.anew.ui.ViewModel.NewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonInfo(newsViewModel: NewsViewModel,onBackToMine:()->Unit={},onNavigateToInfo:()->Unit={},
               onNavigateToPic:()->Unit={}){
    Scaffold (topBar = {
        TopAppBar(
            title = { Text("个人资料",modifier=Modifier.fillMaxWidth(), fontSize = 18.sp, textAlign = TextAlign.Center) },
            navigationIcon = {
                Icon(imageVector = Icons.Default.NavigateBefore,contentDescription = null,
                    modifier=Modifier.clickable {
                        onBackToMine()
                    })
            }
        )
    }){
        Box(modifier=Modifier.padding(it)){
            LazyColumn(){
                item {
                    Divider(color = Color.LightGray, thickness = 0.8f.dp)
                }
                item {
                    InfoList1(title = "头像", value = newsViewModel.currentPic,modifier= Modifier
                        .clickable {
                            onNavigateToPic()
                        }
                        .padding(start = 16.dp))
                    Divider(startIndent = 14.dp, color = Color.LightGray, thickness = 1f.dp)
                }
                item{
                    InfoList(title = "昵称", value = newsViewModel.info.value[0], modifier= Modifier
                        .clickable {
                            newsViewModel.option = 0
                            onNavigateToInfo()
                        }
                        .padding(start = 16.dp, top = 18.dp, bottom = 18.dp))
                    Divider(startIndent = 14.dp, color = Color.LightGray, thickness = 1f.dp)
                }
                item{
                    InfoList(title = "真实姓名", value =  newsViewModel.info.value[1], modifier= Modifier
                        .clickable {
                            newsViewModel.option = 1
                            onNavigateToInfo()
                        }
                        .padding(start = 16.dp, top = 18.dp, bottom = 18.dp))
                    Divider(startIndent = 14.dp, color = Color.LightGray, thickness = 1f.dp)
                }
                item{
                    InfoList(title = "性别", value =  newsViewModel.info.value[2], modifier= Modifier
                        .clickable {
                            newsViewModel.option = 2
                            onNavigateToInfo()
                        }
                        .padding(start = 16.dp, top = 18.dp, bottom = 18.dp))
                    Divider(startIndent = 14.dp, color = Color.LightGray, thickness = 1f.dp)
                }
                item{
                    InfoList(title = "籍贯", value =  newsViewModel.info.value[3], modifier= Modifier
                        .clickable {
                            newsViewModel.option = 3
                            onNavigateToInfo()
                        }
                        .padding(start = 16.dp, top = 18.dp, bottom = 18.dp))
                    Divider(startIndent = 14.dp, color = Color.LightGray, thickness = 1f.dp)
                }
                item{
                    InfoList(title = "个性签名", value =  newsViewModel.info.value[4], modifier= Modifier
                        .clickable {
                            newsViewModel.option = 4
                            onNavigateToInfo()
                        }
                        .padding(start = 16.dp, top = 18.dp, bottom = 18.dp))
                    Divider(startIndent = 14.dp, color = Color.LightGray, thickness = 1f.dp)
                }
                item{
                    InfoList(title = "账号", value =  newsViewModel.info.value[5], modifier= Modifier.padding(start = 16.dp,top=18.dp, bottom = 18.dp))
                    Divider(startIndent = 14.dp, color = Color.LightGray, thickness = 1f.dp)
                }
                item{
                    InfoList(title = "密码", value =  newsViewModel.info.value[6], modifier= Modifier
                        .clickable {
                            newsViewModel.option = 6
                            onNavigateToInfo()
                        }
                        .padding(start = 16.dp, top = 18.dp, bottom = 18.dp))
                    Divider(startIndent = 14.dp, color = Color.LightGray, thickness = 1f.dp)
                }
            }
        }
    }
}

@Composable
fun InfoList(
    title: String,
    value:String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            title,
            fontSize = 17.sp,
            color = Color.DarkGray
        )
        Spacer(Modifier.weight(1f))
        Text(text = value,fontSize = 17.sp,
            color = Color.DarkGray
        )
        androidx.compose.material.Icon(
            imageVector = Icons.Default.ArrowForwardIos, contentDescription = "更多",
            Modifier
                .padding(4.dp, 0.dp, 8.dp, 0.dp)
                .size(16.dp),
            tint = Color.Black
        )
    }
}
@Composable
fun InfoList1(
    title: String,
    value:Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            title,
            fontSize = 17.sp,
            color = Color.DarkGray
        )
        Spacer(Modifier.weight(1f))
        Image(painter = painterResource(id = value), contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape).padding(top=5.dp, bottom = 5.dp))
        androidx.compose.material.Icon(
            imageVector = Icons.Default.ArrowForwardIos, contentDescription = "更多",
            Modifier
                .padding(0.dp, 0.dp, 12.dp, 0.dp)
                .size(16.dp),
            tint = Color.Black
        )
    }
}
