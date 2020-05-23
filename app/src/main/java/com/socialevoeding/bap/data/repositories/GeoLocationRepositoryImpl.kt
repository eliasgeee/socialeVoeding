package com.socialevoeding.bap.data.repositories

import com.socialevoeding.bap.domain.model.LocationModel
import com.socialevoeding.bap.domain.repositories.GeoLocationRepository
import com.socialevoeding.bap.restful.apiServices.GeoLocationApiService
import java.lang.Exception

class GeoLocationRepositoryImpl(private val geoLocationApiService: GeoLocationApiService) : GeoLocationRepository{
    override suspend fun getCurrentGeoLocation(locationModel: LocationModel) : LocationModel {
        try {
            val address = geoLocationApiService.getCurrentGeoLocationAsync(
                latidude = locationModel.latitude,
                longitude =  locationModel.longitude
            ).await().address.city
            locationModel.cityName = address
        }
        catch (e : Exception){
            e.message
        }
        return locationModel
    }
}