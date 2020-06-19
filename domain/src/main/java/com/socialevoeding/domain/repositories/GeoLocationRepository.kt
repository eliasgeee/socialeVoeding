package com.socialevoeding.domain.repositories

import com.socialevoeding.domain.model.LocationModel
import com.socialevoeding.domain.model.Result

interface GeoLocationRepository {

    suspend fun getCurrentGeoLocation(locationModel: LocationModel) : LocationModel
}