package com.socialevoeding.usecases

import com.socialevoeding.domain.model.PlaceLocation
import com.socialevoeding.domain.repositories.UserLocationRepository
import com.socialevoeding.usecases.base.UseCase

class GetCurrentPlaceNameUseCase(
    private val userLocationRepository: UserLocationRepository
) : UseCase<PlaceLocation>(){
    var currentPlaceLocation : PlaceLocation? = null
    override suspend fun executeOnBackground(): PlaceLocation {
        return userLocationRepository.getCurrentGeoLocation(currentPlaceLocation!!)
    }
}