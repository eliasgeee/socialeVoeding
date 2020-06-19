package com.socialevoeding.usecases

import com.socialevoeding.domain.model.LocationModel
import com.socialevoeding.domain.repositories.GeoLocationRepository
import com.socialevoeding.usecases.base.UseCase
import com.socialevoeding.domain.model.Result

class GetCurrentPlaceNameUseCase(
    private val geoLocationRepository: GeoLocationRepository
) : UseCase<LocationModel>(){
    var currentLocationModel : LocationModel? = null
    override suspend fun executeOnBackground(): LocationModel {
        return geoLocationRepository.getCurrentGeoLocation(currentLocationModel!!)
    }
}