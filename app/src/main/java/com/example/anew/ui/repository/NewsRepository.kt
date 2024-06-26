package com.example.anew.ui.repository

import com.example.anew.ui.Entity.News
import com.example.anew.ui.network.NewsApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class NewsRepository {
    companion object{
        fun getNews(channel:String,appkey:String,num:Int):Flow<News> =
            flow{
                var news=NewsApiClient.newsApiService.getNewsData(
                    channel,
                    appkey,
                    num
                )
                emit(news)
            }.flowOn(Dispatchers.IO)
    }
}