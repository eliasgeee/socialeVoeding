package com.socialevoeding.bap.data.repositories

import com.socialevoeding.bap.domain.model.LocationModel
import com.socialevoeding.bap.domain.repositories.GeoLocationRepository
import com.socialevoeding.bap.restful.apiServices.GeoLocationApiService
import java.lang.Exception

class GeoLocationRepositoryImpl(private val geoLocationApiService: GeoLocationApiService) : GeoLocationRepository{
    override suspend fun getCurrentGeoLocation(locationModel: LocationModel) : String {
        var address : String = ""
        try {
            address = geoLocationApiService.getCurrentGeoLocation(
                latidude = locationModel.latitude,
                longitude =  locationModel.longitude
            ).await().address.city
        }
        catch (e : Exception){
            e.message
        }
        return address
    }
}