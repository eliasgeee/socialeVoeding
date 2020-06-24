package com.socialevoeding.usecases

import com.socialevoeding.domain.model.Place
import com.socialevoeding.domain.repositories.PlaceRepository
import com.socialevoeding.usecases.base.UseCase

class GetPlacesFromLocalDatabaseUseCase(
    private val placeRepository: PlaceRepository
) : UseCase<MutableList<Place>>() {
    override suspend fun executeOnBackground(): MutableList<Place> {
        return placeRepository.getPlacesFromLocalDatabase()
    }
}
