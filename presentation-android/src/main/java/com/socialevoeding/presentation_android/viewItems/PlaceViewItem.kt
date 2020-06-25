package com.socialevoeding.presentation_android.viewItems

import com.socialevoeding.domain.model.OpeningDay

data class PlaceViewItem(
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