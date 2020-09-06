package com.socialevoeding.domain.repositories

import com.socialevoeding.domain.model.place.Place
import com.socialevoeding.domain.model.UserLocation
import kotlinx.coroutines.flow.Flow

interface PlaceRepository {
    suspend fun refreshPlaces(userLocation: UserLocation, currentCategoryName: String)
    suspend fun getPlacesFromLocalDatabase(): Flow<List<Place>>
    suspend fun insertPlacesIntoDatabase(places: List<Place>)
}