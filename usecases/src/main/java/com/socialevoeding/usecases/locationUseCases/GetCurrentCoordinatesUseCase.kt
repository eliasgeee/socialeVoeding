package com.socialevoeding.usecases.locationUseCases

import com.socialevoeding.domain.model.Coordinates
import com.socialevoeding.domain.repositories.UserLocationRepository
import com.socialevoeding.usecases.base.UseCase

class GetCurrentCoordinatesUseCase(
    private val userLocationRepository: UserLocationRepository
) : UseCase<Coordinates>() {
    override suspend fun executeOnBackground(): Coordinates {
        return userLocationRepository.getCurrentUserCoordinates()
    }
}