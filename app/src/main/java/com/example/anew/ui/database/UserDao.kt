package com.example.anew.ui.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User):Long

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("select * from users")
    fun queryAllUsers():List<User>

    @Query("select * from users where userName = :userName")
    fun queryUserByName(userName:String):User
}