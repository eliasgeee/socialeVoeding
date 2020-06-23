package com.socialevoeding.domain.model

import java.io.Serializable

class Place(
    val id: String,
    val name: String,
    val telephoneNumber: String,
    val webUrl: String,
    val img : String,
    val placeLocation: PlaceLocation,
    var openingHours: Array<OpeningDay>
) : Serializable