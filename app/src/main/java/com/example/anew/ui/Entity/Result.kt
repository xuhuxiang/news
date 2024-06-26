package com.example.anew.ui.Entity

import com.google.gson.annotations.SerializedName

data class Result(@SerializedName("num")
                  val num: String = "",
                  @SerializedName("channel")
                  val channel: String = "",
                  @SerializedName("list")
                  val list: List<ListItem>?)