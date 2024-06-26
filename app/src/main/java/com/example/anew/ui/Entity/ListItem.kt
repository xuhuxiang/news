package com.example.anew.ui.Entity

import com.google.gson.annotations.SerializedName

data class ListItem(@SerializedName("src")
                    val src: String = "",
                    @SerializedName("weburl")
                    val weburl: String = "",
                    @SerializedName("time")
                    val time: String = "",
                    @SerializedName("pic")
                    val pic: String = "",
                    @SerializedName("title")
                    val title: String = "",
                    @SerializedName("category")
                    val category: String = "",
                    @SerializedName("content")
                    val content: String = "",
                    @SerializedName("url")
                    val url: String = "")