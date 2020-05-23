package com.socialevoeding.bap.usecases

import com.socialevoeding.bap.domain.repositories.PlaceRepository
import com.socialevoeding.bap.usecases.base.ErrorMapper
import com.socialevoeding.bap.usecases.base.UseCase

class RefreshPlacesUseCase(
    errorMapper: ErrorMapper,
    private val placeRepository: PlaceRepository
) : UseCase<Boolean>(errorMapper){
    var currentLocationName : String = ""
    var currentCategorieName : String = ""
    var currentQueryNames : List<String> = emptyList()

    override suspend fun executeOnBackground(): Boolean {
        return placeRepository.refreshPlaces(currentLocationName, currentCategorieName, currentQueryNames)
    }
}
