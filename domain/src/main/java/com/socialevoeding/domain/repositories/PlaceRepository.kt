package com.socialevoeding.domain.repositories

import com.socialevoeding.domain.model.PlaceLocation
import com.socialevoeding.domain.model.Place

interface PlaceRepository {
    suspend fun refreshPlaces(currentPlaceLocation : PlaceLocation, currentCategoryName : String, currentQueryNames : List<String>)
    suspend fun getPlacesFromLocalDatabase() : MutableList<Place>
    suspend fun insertPlacesIntoDatabase(places: List<Place>)
}