package com.example.anew.ui.component



import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.anew.ui.ViewModel.NewsState
import com.example.anew.ui.ViewModel.NewsViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask



@OptIn(ExperimentalPagerApi::class)
@Composable
fun SwiperContent(vm:NewsViewModel,onNavigateToArticle:()->Unit={}) {
    //虚拟页数·
    val virtualCount = Int.MAX_VALUE
    //实际页数
    val actualCount=vm.num
    //初始图片下表
    val initialIndex=virtualCount/2
    val coroutineScope= rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage =initialIndex)
    DisposableEffect(Unit){
        val timer = Timer()
        timer.schedule(object : TimerTask(){
            override fun run() {
                coroutineScope.launch {pagerState.scrollToPage(pagerState.currentPage+1)  }
            }
        },3000,3000)
        onDispose {
            timer.cancel()
        }
    }
    HorizontalPager(count = virtualCount,
        state=pagerState,
        modifier= Modifier
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
            index->
        val actualIndex=(index-initialIndex+actualCount)%actualCount
        AsyncImage(
            model =when(val result=vm.newsData.value){
                is NewsState.Success -> {
                    result.news.result.list!!.get(actualIndex).pic
                }
                is NewsState.Failure->{

                }
                is NewsState.Loading->{

                }

                else -> {}
            },
            contentDescription = null,
            modifier= Modifier
                .fillMaxWidth()
                .aspectRatio(16 / 9f)
                .clickable {
                    vm.updateId(actualIndex)
                    onNavigateToArticle.invoke()
                }, contentScale = ContentScale.Crop,
        )
        Row (modifier=Modifier.padding(top=180.dp).fillMaxWidth()
            .background(Color.Gray.copy(alpha = 0.4f))
            .height(40.dp).padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically){
            Text(text =when(val result=vm.newsData.value){
                is NewsState.Success -> {
                    result.news.result.list!!.get(actualIndex).title
                }
                is NewsState.Failure->{
                    ""
                }
                is NewsState.Loading->{
                    ""
                }

                else -> {""}
            } ,
                maxLines=1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}