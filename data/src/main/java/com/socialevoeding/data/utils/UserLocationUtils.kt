package com.socialevoeding.data.utils

import com.socialevoeding.domain.model.Coordinates
import com.socialevoeding.domain.model.UserLocation

fun String.createUserLocation(coordinates: Coordinates): UserLocation {
    return UserLocation(
        latitude = coordinates.latitude,
        longitude = coordinates.longitude,
        cityName = this
    )
}