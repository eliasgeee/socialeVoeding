package com.socialevoeding.usecases.placeUseCases

import com.socialevoeding.domain.model.place.Place
import com.socialevoeding.domain.repositories.PlaceRepository
import com.socialevoeding.usecases.base.UseCase
import kotlinx.coroutines.flow.Flow

class GetPlacesUseCase(
    private val placeRepository: PlaceRepository
) : UseCase<Flow<List<Place>>>() {
    override suspend fun executeOnBackground(): Flow<List<Place>> = placeRepository.getPlacesFromLocalDatabase()
}
