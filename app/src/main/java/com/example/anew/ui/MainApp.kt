package com.example.anew.ui

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class MainApp: Application() {
    @SuppressLint("StaticFieldLeaked")
    companion object{
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}
