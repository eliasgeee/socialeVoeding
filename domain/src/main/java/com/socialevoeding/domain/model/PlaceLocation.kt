package com.socialevoeding.domain.model

data class PlaceLocation(
    val address: String,
    var cityName: String,
    val latitude: Double,
    val longitude: Double
)