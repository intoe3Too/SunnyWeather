package com.gx.sunnyweather.logic.model

import com.google.gson.annotations.SerializedName

/**
 * get query result json map
 */
data class PlaceResponse(val status: String, val query: String, val places:List<Place>)

data class Place(val id: String, val loc: Location, @SerializedName("place_id") val placeId: String,
                 @SerializedName("formatted_address") val address: String, val name: String)

data class Location(val latitude: String, val longitude: String)