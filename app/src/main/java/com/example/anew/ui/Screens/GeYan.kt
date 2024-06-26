package com.example.anew.ui.Screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.anew.ui.MainApp
import com.example.anew.ui.Screens.ui.theme.NewTheme
import com.example.anew.ui.ViewModel.NewsViewModel
import com.example.anew.ui.database.Info
import com.example.anew.ui.database.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeYan(newsViewModel: NewsViewModel,onBackToMine:()->Unit={}){
    val userInfo= UserDatabase.getInstance(MainApp.context).getInfoDao()
    val context= LocalContext.current
    Scaffold (topBar = {
        TopAppBar(
            title = { Text("座右铭",modifier=Modifier.fillMaxWidth(), fontSize = 18.sp, textAlign = TextAlign.Center) },
            navigationIcon = {
                Icon(imageVector = Icons.Default.NavigateBefore,contentDescription = null,
                    modifier=Modifier.clickable {
                        onBackToMine()
                    })
            }
        )
    }) {
        Box(modifier = Modifier.fillMaxSize().padding(it), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                TextField(value = newsViewModel.geyan, onValueChange = {
                    newsViewModel.updateGeyan(it)
                })
                Button(modifier=Modifier.height(50.dp).width(140.dp).padding(top=8.dp).clip(RoundedCornerShape(16.dp)),shape= RectangleShape,onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        var user=userInfo.queryUserByName(newsViewModel.info.value[5])
                        user.ge=newsViewModel.geyan
                        userInfo.updateUserInfo(user)
                    }
                    Toast.makeText(context,"座右铭设置成功", Toast.LENGTH_LONG).show()
                    onBackToMine.invoke()
                }) {
                    Text(text = "发布")
                }
            }
        }
    }
}