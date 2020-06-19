package com.socialevoeding.bap.data.repositories

import com.socialevoeding.domain.model.LocationModel
import com.socialevoeding.domain.repositories.GeoLocationRepository
import com.socialevoeding.bap.restful.apiServices.GeoLocationApiService
import java.lang.Exception
import com.socialevoeding.domain.model.Result

class GeoLocationRepositoryImpl(private val geoLocationApiService: GeoLocationApiService) :
    GeoLocationRepository {
    override suspend fun getCurrentGeoLocation(locationModel: LocationModel) : LocationModel {
        val address = geoLocationApiService.getCurrentGeoLocationAsync(
            latidude = locationModel.latitude,
            longitude = locationModel.longitude
        ).await().address.city
        locationModel.cityName = address
        return locationModel
    }
}