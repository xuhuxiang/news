package com.example.anew.ui.Entity

import com.google.gson.annotations.SerializedName

data class News(@SerializedName("msg")
                val msg: String = "",
                @SerializedName("result")
                val result: Result,
                @SerializedName("status")
                val status: Int = 0)