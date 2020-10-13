package com.gx.sunnyweather.logic.network

import com.gx.sunnyweather.SunnyWeatherApplication
import com.gx.sunnyweather.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {
    @GET("v2/place?token=${SunnyWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>

    @GET("v2/place?query={query}&token=${SunnyWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlacesV2(@Query("query") query: String): Call<PlaceResponse>
}