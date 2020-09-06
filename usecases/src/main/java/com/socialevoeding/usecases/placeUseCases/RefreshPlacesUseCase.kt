package com.socialevoeding.usecases.placeUseCases

import com.socialevoeding.domain.model.UserLocation
import com.socialevoeding.domain.repositories.PlaceRepository
import com.socialevoeding.usecases.base.UseCase

class RefreshPlacesUseCase(
    private val placeRepository: PlaceRepository
) : UseCase<Unit>() {

    var userLocation: UserLocation? = null
    var currentCategoryName: String = ""

    override suspend fun executeOnBackground() {
        return placeRepository.refreshPlaces(userLocation = userLocation!!, currentCategoryName = currentCategoryName)
    }
}
