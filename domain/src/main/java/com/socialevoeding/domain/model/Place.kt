package com.socialevoeding.domain.model

class Place(
    val name: String,
    val telephoneNumber: String,
    val webUrl: String,
    val img: String,
    val address: String,
    var cityName: String,
    val latitude: Double,
    val longitude: Double,
    var openingHours: Array<OpeningDay>
)