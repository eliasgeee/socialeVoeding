package com.socialevoeding.bap.usecases

import com.socialevoeding.bap.domain.model.LocationModel
import com.socialevoeding.bap.domain.repositories.GPSRepository
import com.socialevoeding.bap.domain.repositories.GeoLocationRepository
import com.socialevoeding.bap.usecases.base.ErrorMapper
import com.socialevoeding.bap.usecases.base.UseCase

class GetCurrentPlaceNameUseCase(
    errorMapper: ErrorMapper,
    private val geoLocationRepository: GeoLocationRepository
) : UseCase<String>(errorMapper){
    var currentLocationModel : LocationModel? = null
    override suspend fun executeOnBackground(): String {
        return geoLocationRepository.getCurrentGeoLocation(currentLocationModel!!)
    }
}