package com.example.anew.ui.Screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.layoutId
import coil.compose.AsyncImage
import com.example.anew.ui.MainApp
import com.example.anew.ui.ViewModel.NewsViewModel
import com.example.anew.ui.database.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Pictures(newsViewModel: NewsViewModel,onBackToMine:()->Unit={}){
    val context= LocalContext.current
    val userInfo= UserDatabase.getInstance(MainApp.context).getInfoDao()
    Scaffold (topBar = {
        TopAppBar(
            title = { Text("选择照片",modifier=Modifier.fillMaxWidth(), fontSize = 18.sp, textAlign = TextAlign.Center) },
            navigationIcon = {
                Icon(imageVector = Icons.Default.NavigateBefore,contentDescription = null,
                    modifier=Modifier.clickable {
                        onBackToMine()
                    })
            }
        )
    }){
        Box(modifier = Modifier.fillMaxSize().padding(it)){
            LazyColumn(){
                items(newsViewModel.pic){
                    Card (modifier= Modifier
                        .padding(8.dp, 8.dp, 8.dp, 8.dp)
                        .fillMaxWidth()
                        .height(200.dp),
                        colors = CardDefaults.cardColors(Color(0xFFFFFFFF))){
                        AsyncImage(
                            model = it, contentDescription = null, modifier = Modifier
                                .layoutId("img")
                                .aspectRatio(14 / 10f)
                                .clip(RoundedCornerShape(8.dp)).clickable {
                                    newsViewModel.updatePic(it)
                                    CoroutineScope(Dispatchers.IO).launch {
                                        var user=userInfo.queryUserByName(newsViewModel.info.value[5])
                                        user.pic=it
                                        userInfo.updateUserInfo(user)
                                    }
                                    Toast.makeText(context,"头像设置成功",Toast.LENGTH_LONG).show()
                                    onBackToMine()
                                }
                        )
                    }
                }
            }

        }
    }

}
//@Preview
//@Composable
//fun Pre(){
//    Pictures()
//}
