package com.example.anew.ui.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface InfoDao {
    @Insert
    fun insertUserInfo(user: Info):Long
    @Update
    fun updateUserInfo(user: Info)
    @Delete
    fun deleteUserInfo(user: Info)
    @Query("select * from userInfo")
    fun queryAllUsers():List<Info>
    @Query("select * from userInfo where userName = :userName")
    fun queryUserByName(userName:String):Info
}