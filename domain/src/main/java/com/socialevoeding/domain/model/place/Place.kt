package com.socialevoeding.domain.model.place

import com.socialevoeding.domain.model.OpeningDay

/**
 * Builder pattern instead of apply because we want to keep creation in the domain layer, enforce domain rules
 * and make the creation of a Place object dynamic
 */

fun buildPlace(lambda: PlaceBuilder.() -> Unit) =
    PlaceBuilder()
        .apply(lambda)
        .build()

class Place(builder: PlaceBuilder) {

    var name: String = ""
    private set

    var cityName: String = ""
    private set

    var latitude: Double = Double.NaN
    private set

    var longitude: Double = Double.NaN
    private set

    var openingHours: Array<OpeningDay>? = emptyArray()
    private set

    var telephoneNumber: String? = ""
    private set

    var webUrl: String? = ""
    private set

    var img: String? = ""
    private set

    var address: String? = ""
    private set

    init {
        name = builder.name
        cityName = builder.cityName
        latitude = builder.latitude
        longitude = builder.longitude
        openingHours = builder.openingHours
        telephoneNumber = builder.telephoneNumber
        webUrl = builder.webUrl
        img = builder.img
        address = builder.img
    }
}