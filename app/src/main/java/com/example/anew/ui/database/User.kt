package com.example.anew.ui.database

import android.accounts.Account
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "users",indices = [Index(value = ["userName"], unique = true)])
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="userId") val id:Long,
    @ColumnInfo(name="userName") val name:String,
    @ColumnInfo(name="userPassword") var password:String
){
    @Ignore
    constructor(act:String,password:String):this(0,act,password)
}
