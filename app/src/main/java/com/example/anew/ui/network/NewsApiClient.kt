package com.example.anew.ui.network

import com.example.anew.ui.Entity.News
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object NewsApiClient {
    private val BaseUrl="https://api.jisuapi.com/news/"
    private val moshi= Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val retrofit: Retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
    val newsApiService:NewsApiService by lazy {
        retrofit.create(NewsApiService::class.java)
    }
}
interface NewsApiService{
    @GET("get")
    suspend fun getNewsData(@Query("channel") channel:String,
                            @Query("appkey") appkey:String,
                            @Query("num") num:Int
                            ): News
}