package com.socialevoeding.usecases.locationUseCases

import com.socialevoeding.domain.model.Coordinates
import com.socialevoeding.domain.model.PlaceLocation
import com.socialevoeding.domain.model.UserLocation
import com.socialevoeding.domain.repositories.UserLocationRepository
import com.socialevoeding.usecases.base.UseCase

class GetCurrentGeoLocationUseCase(
    private val userLocationRepository: UserLocationRepository
) : UseCase<UserLocation>() {
    var currentCoordinates : Coordinates? = null
    override suspend fun executeOnBackground(): UserLocation {
        return userLocationRepository.getCurrentGeoLocation(currentCoordinates!!)
    }
}