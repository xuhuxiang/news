package com.example.anew.ui.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [User::class,Info::class], version = 1)
abstract class UserDatabase :RoomDatabase(){
    abstract fun getUserDao(): UserDao
    abstract fun getInfoDao(): InfoDao
    companion object{
        private var instance: UserDatabase? = null
        /*** 单例模式创建为一个UserDatabase对象实例*/
        @Synchronized
        fun getInstance(context: Context): UserDatabase {
            instance?.let{
                return it
            }
            return Room.databaseBuilder(
                context,
                UserDatabase::class.java,
                "userDB3.db" //写入文件中的文件名
            ).build()
        }
    }

}