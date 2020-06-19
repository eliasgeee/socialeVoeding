package com.socialevoeding.usecases

import com.socialevoeding.domain.model.Place
import com.socialevoeding.domain.repositories.PlaceRepository
import com.socialevoeding.usecases.base.UseCase
import com.socialevoeding.domain.model.Result

class InsertPlacesIntoLocalDatabaseUseCase (
    private val placesRepository: PlaceRepository
) : UseCase<Unit>() {
    var places : List<Place> = emptyList()
    override suspend fun executeOnBackground() {
        return placesRepository.insertPlacesIntoDatabase(places)
    }
}