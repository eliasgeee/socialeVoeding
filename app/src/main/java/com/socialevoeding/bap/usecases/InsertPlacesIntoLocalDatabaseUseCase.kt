package com.socialevoeding.bap.usecases

import com.socialevoeding.bap.domain.model.Place
import com.socialevoeding.bap.domain.repositories.PlaceRepository
import com.socialevoeding.bap.usecases.base.ErrorMapper
import com.socialevoeding.bap.usecases.base.UseCase

class InsertPlacesIntoLocalDatabaseUseCase (
    errorMapper: ErrorMapper,
    private val placesRepository: PlaceRepository
) : UseCase<Boolean>(errorMapper) {
    var places = ArrayList<Place>()
    override suspend fun executeOnBackground(): Boolean {
        return placesRepository.insertPlacesIntoDatabase(places)
    }
}