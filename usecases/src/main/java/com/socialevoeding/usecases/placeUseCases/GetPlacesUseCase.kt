package com.socialevoeding.usecases.placeUseCases

import com.socialevoeding.domain.model.Place
import com.socialevoeding.domain.repositories.PlaceRepository
import com.socialevoeding.usecases.base.UseCase

class GetPlacesUseCase(
    private val placeRepository: PlaceRepository
) : UseCase<MutableList<Place>>() {
    override suspend fun executeOnBackground(): MutableList<Place> {
        return placeRepository.getPlacesFromLocalDatabase()
    }
}
