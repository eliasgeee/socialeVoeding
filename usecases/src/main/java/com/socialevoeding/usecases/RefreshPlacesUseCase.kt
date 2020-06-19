package com.socialevoeding.usecases

import com.socialevoeding.domain.model.LocationModel
import com.socialevoeding.domain.repositories.PlaceRepository
import com.socialevoeding.usecases.base.UseCase
import com.socialevoeding.domain.model.Result

class RefreshPlacesUseCase(
    private val placeRepository: PlaceRepository
) : UseCase<Unit>(){
    var currentLocationModel : LocationModel? = null
    var currentCategorieName : String = ""
    var currentQueryNames : List<String> = emptyList()

    override suspend fun executeOnBackground() {
        return placeRepository.refreshPlaces(currentLocationModel!!, currentCategorieName, currentQueryNames)
    }
}
