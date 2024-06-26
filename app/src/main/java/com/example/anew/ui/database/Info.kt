package com.example.anew.ui.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "userInfo", foreignKeys = [
    ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("userName"),
        childColumns = arrayOf("userName"),
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )
], indices = [Index(value=["userName"])]
)
data class Info(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="userId") val id:Long,
    @ColumnInfo(name="userName") val name:String,
    @ColumnInfo(name="userPic") var pic:Int=-1,
    @ColumnInfo(name="userGe") var ge:String="",
    @ColumnInfo(name="nicheng") var nicheng:String="",
    @ColumnInfo(name="userReality") var userReality:String="",
    @ColumnInfo(name="sex") var sex:String="",
    @ColumnInfo(name="region") var region:String="",
    @ColumnInfo(name="qianming") var qianming:String="",
){
    @Ignore
    constructor(name:String,pic:Int,ge:String,nicheng: String,userReality: String,sex: String,region: String,qianming: String):this(0,name,pic,ge,nicheng,userReality,sex,region,qianming)
}
