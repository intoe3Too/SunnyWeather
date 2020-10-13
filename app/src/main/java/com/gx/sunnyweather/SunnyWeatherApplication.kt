package com.gx.sunnyweather

import android.app.Application
import android.content.Context

class SunnyWeatherApplication : Application(){
    companion object {
        lateinit var context: Context
        const val TOKEN : String = "o3ktRUYAeCsO7ecU";
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}