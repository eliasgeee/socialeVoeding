package com.socialevoeding.usecases.placeUseCases

import com.socialevoeding.domain.model.place.Place
import com.socialevoeding.domain.repositories.PlaceRepository
import com.socialevoeding.usecases.base.UseCase

class SavePlacesUseCase(
    private val placesRepository: PlaceRepository
) : UseCase<Unit>() {
    var places: List<Place> = emptyList()

    override suspend fun executeOnBackground() = placesRepository.insertPlacesIntoDatabase(places)
}