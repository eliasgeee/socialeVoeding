package com.socialevoeding.usecases.locationUseCases

import com.socialevoeding.util_models.Either
import com.socialevoeding.domain.model.UserLocation
import com.socialevoeding.domain.repositories.UserLocationRepository
import com.socialevoeding.usecases.base.UseCase

class GetLastKnownUserLocationUseCase(
    private val userLocationRepository: UserLocationRepository
) : UseCase<Either<Unit, UserLocation>>() {
    override suspend fun executeOnBackground(): Either<Unit, UserLocation> {
        return userLocationRepository.getLastKnownUserLocation()
    }
}
