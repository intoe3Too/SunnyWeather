package com.gx.sunnyweather.logic.network

import com.gx.sunnyweather.SunnyWeatherApplication
import com.gx.sunnyweather.logic.model.DailyResponse
import com.gx.sunnyweather.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {

    @GET("v2.5/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(
        @Path("lat") lat: String, @Path("lng") lng: String
    ): Call<RealtimeResponse>

    @GET("v2.5/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lat") lat: String,
                        @Path("lng") lng: String
                        ): Call<DailyResponse>
}