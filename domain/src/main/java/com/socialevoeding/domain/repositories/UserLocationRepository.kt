package com.socialevoeding.domain.repositories

import com.socialevoeding.domain.model.PlaceLocation

interface UserLocationRepository {

    suspend fun getCurrentGeoLocation(placeLocation: PlaceLocation): PlaceLocation
}