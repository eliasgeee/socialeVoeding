package com.socialevoeding.domain.repositories

import com.socialevoeding.domain.model.Place
import com.socialevoeding.domain.model.UserLocation

interface PlaceRepository {
    suspend fun refreshPlaces(userLocation: UserLocation, currentCategoryName: String)
    suspend fun getPlacesFromLocalDatabase(): MutableList<Place>
    suspend fun insertPlacesIntoDatabase(places: List<Place>)
}