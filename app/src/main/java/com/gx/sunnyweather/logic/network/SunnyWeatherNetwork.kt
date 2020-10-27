package com.gx.sunnyweather.logic.network

import com.gx.sunnyweather.logic.model.Place
import com.gx.sunnyweather.logic.model.Weather
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object SunnyWeatherNetwork {
    private val placeService = ServiceCreator.create(PlaceService::class.java)
    suspend fun searchPlaces(query: String) = placeService.searchPlaces(query).await()

    private val weatherService = ServiceCreator.create(WeatherService::class.java)
    suspend fun getRealtimeWeather(lat: String, lng: String) =
        weatherService.getRealtimeWeather(lat, lng).await()

    suspend fun getDailyWeather(lat: String, lng: String) =
        weatherService.getDailyWeather(lat, lng).await()

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) {
                        continuation.resume(body)
                    } else {
                        continuation.resumeWithException(
                            RuntimeException("response body is null")
                        )
                    }
                }
            })
        }
    }
}