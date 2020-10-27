package com.gx.sunnyweather.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.gx.sunnyweather.logic.Repository
import com.gx.sunnyweather.logic.model.Location

class WeatherViewModel : ViewModel() {
    private val locationLiveData = MutableLiveData<Location>()
    var locationLng = ""
    var locationLat = ""
    var placeName = ""

    val weatherLiveData = Transformations.switchMap(locationLiveData) { location ->
        Repository.refreshWeather( location.latitude, location.longitude)
    }

    fun refreshWeather(lat: String, lng: String) {
        locationLiveData.value = Location(lat, lng)
    }
}