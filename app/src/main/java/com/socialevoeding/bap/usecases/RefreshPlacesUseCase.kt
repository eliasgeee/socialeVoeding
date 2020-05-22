package com.socialevoeding.bap.usecases

import com.socialevoeding.bap.domain.repositories.PlaceRepository
import com.socialevoeding.bap.usecases.base.ErrorMapper
import com.socialevoeding.bap.usecases.base.UseCase

class RefreshPlacesUseCase(
    errorMapper: ErrorMapper,
    private val placeRepository: PlaceRepository
) : UseCase<Boolean>(errorMapper){
    override suspend fun executeOnBackground(): Boolean {
        return placeRepository.refreshPlaces()
    }
}
