package com.socialevoeding.bap.usecases

import com.socialevoeding.bap.domain.model.Place
import com.socialevoeding.bap.domain.repositories.PlaceRepository
import com.socialevoeding.bap.usecases.base.ErrorMapper
import com.socialevoeding.bap.usecases.base.UseCase

class GetPlacesFromLocalDatabaseUseCase (
    errorMapper: ErrorMapper,
    private val placeRepository: PlaceRepository
) : UseCase<MutableList<Place>>(errorMapper) {
    override suspend fun executeOnBackground(): MutableList<Place> {
        return placeRepository.getPlacesFromLocalDatabase()
    }
}
