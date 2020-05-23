package com.socialevoeding.bap.domain.repositories

import com.socialevoeding.bap.domain.model.LocationModel

interface GeoLocationRepository {

    suspend fun getCurrentGeoLocation(locationModel: LocationModel) : LocationModel
}