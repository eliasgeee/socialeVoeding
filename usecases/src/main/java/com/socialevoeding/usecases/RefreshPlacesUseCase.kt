package com.socialevoeding.usecases

import com.socialevoeding.domain.model.PlaceLocation
import com.socialevoeding.domain.repositories.PlaceRepository
import com.socialevoeding.usecases.base.UseCase

class RefreshPlacesUseCase(
    private val placeRepository: PlaceRepository
) : UseCase<Unit>(){
    var currentPlaceLocation : PlaceLocation? = null
    var currentCategorieName : String = ""
    var currentQueryNames : List<String> = emptyList()

    override suspend fun executeOnBackground() {
        return placeRepository.refreshPlaces(currentPlaceLocation!!, currentCategorieName, currentQueryNames)
    }
}
