package com.socialevoeding.bap.data.repositories

import com.socialevoeding.bap.domain.model.LocationModel
import com.socialevoeding.bap.domain.repositories.GeoLocationRepository
import com.socialevoeding.bap.restful.apiServices.GeoLocationApiService

class GeoLocationRepositoryImpl(private val geoLocationApiService: GeoLocationApiService) : GeoLocationRepository{
    override suspend fun getCurrentGeoLocation(locationModel: LocationModel) {

    }
}