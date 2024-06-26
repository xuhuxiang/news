package com.example.anew.ui.Screens


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.DeviceUnknown
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.TextSnippet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.anew.ui.ViewModel.NewsViewModel
import com.example.anew.ui.component.TopAppBar

@Composable
fun MineScreen(newsViewModel: NewsViewModel,onNavigateToPic:()->Unit={},
               onNavigateToGe:()->Unit={},onNavigateToAbout:()->Unit={},onExit:()->Unit={},
               onNavigateToPersonInfo:()->Unit={}){
    if (newsViewModel.showExitDialog) {
        // 显示退出确认弹窗
        AlertDialog(
            onDismissRequest = { newsViewModel.onShowExitDialogChange(false) },
            title = { Text("退出应用") },
            text = { Text("确定要退出应用吗？") },
            confirmButton = {
                TextButton(
                    onClick = {
                        newsViewModel.onShowExitDialogChange(false)
                        onExit() // 用户确认退出
                    }
                ) {
                    Text("退出")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { newsViewModel.onShowExitDialogChange(false) } // 用户取消退出
                ) {
                    Text("取消")
                }
            }
        )
    }
    Column() {
        TopAppBar {
            Text(text = "我的")
        }
        LazyColumn() {
            item {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp) ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp, 8.dp, 8.dp, 8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = newsViewModel.currentPic),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(100.dp)
                                .clip(RoundedCornerShape(16.dp))
                        )
                        Column(verticalArrangement = Arrangement.spacedBy(30.dp),
                            modifier=Modifier.padding(16.dp)) {
                            Text(text = "欢迎你 ${newsViewModel.info.value[1]}", fontSize = 16.sp)
                            Text(text = "座右铭:${newsViewModel.geyan}", fontSize = 16.sp)
                        }
                    }
                }
            }
            item {
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp))
            }
            item {
                MeListItem(icon = Icons.Default.TextSnippet, title = "座右铭设置",modifier=Modifier.clickable {
                    onNavigateToGe.invoke()
                })
                Divider(startIndent = 56.dp, color = Color.LightGray, thickness = 0.8f.dp)
            }

            item {
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp))
            }
            item {
                MeListItem(icon = Icons.Default.Image, title = "头像设置",modifier=Modifier.clickable {
                    onNavigateToPic.invoke()
                    Log.d("TAG","11111111")
                })
                Divider(startIndent = 56.dp, color = Color.LightGray, thickness = 0.8f.dp)
            }
            item {
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp))
            }
            item{
                MeListItem(icon = Icons.Default.Description, title = "简介设置",modifier=Modifier.clickable {
                    onNavigateToPersonInfo()
                })
                Divider(startIndent = 56.dp, color = Color.LightGray, thickness = 0.8f.dp)
            }
            item {
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp))
            }
            item{
                MeListItem(icon = Icons.Default.DeviceUnknown, title = "关于APP",modifier=Modifier.clickable {
                    onNavigateToAbout.invoke()
                })
                Divider(startIndent = 56.dp, color = Color.LightGray, thickness = 0.8f.dp)
            }
            item {
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp))
            }
            item{
                MeListItem(icon = Icons.Default.ExitToApp, title = "退出APP",modifier=Modifier.clickable {
                    //退出到登录界面
                    newsViewModel.showExitDialog=true
                })
                Divider(startIndent = 56.dp, color = Color.LightGray, thickness = 0.8f.dp)
            }
        }
    }


}

@Composable
fun MeListItem(
    icon: ImageVector, title: String, modifier: Modifier = Modifier,
) {
    Row(
        modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            icon, "title", Modifier.padding(12.dp, 8.dp, 8.dp, 8.dp).size(36.dp).padding(8.dp)
        )
        Text(title, fontSize = 17.sp, color = Color.DarkGray
        )
        Spacer(Modifier.weight(1f))
        Icon(imageVector = Icons.Default.ArrowForwardIos, contentDescription = "更多", Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp).size(16.dp), tint = Color.Black
        )
    }
}

//@Preview
//@Composable
//fun MinePreview(){
//    MineScreen()
//}
