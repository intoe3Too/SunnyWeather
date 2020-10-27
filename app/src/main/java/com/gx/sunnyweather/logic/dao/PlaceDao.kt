package com.gx.sunnyweather.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.gx.sunnyweather.SunnyWeatherApplication
import com.gx.sunnyweather.logic.model.Place

object PlaceDao {
    fun savePlace(place: Place){
        sharedPreferences().edit {
            putString("place", Gson().toJson(place))
        }
    }

    fun getSavedPlace(): Place{
        return Gson().fromJson(sharedPreferences().getString("place",""), Place::class.java)
    }

    fun isPlaceSaved() = sharedPreferences().contains("place")

    private fun sharedPreferences() =
        SunnyWeatherApplication.context.getSharedPreferences("SunnyWeather", Context.MODE_PRIVATE)
}