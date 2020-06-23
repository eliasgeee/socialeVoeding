package com.socialevoeding.data.dtos.remote

data class GeolocationObject(
    val networkGeolocationAddress: NetworkGeolocationAddress,
    val boundingbox: List<String>,
    val display_name: String,
    val lat: String,
    val licence: String,
    val lon: String,
    val osm_id: Int,
    val osm_type: String,
    val place_id: Int
)

data class NetworkGeolocationAddress(
    val city: String,
    val city_district: String,
    val country: String,
    val country_code: String,
    val county: String,
    val postcode: String,
    val road: String,
    val state: String
)