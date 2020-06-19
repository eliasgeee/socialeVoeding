package com.socialevoeding.domain.repositories

import com.socialevoeding.domain.model.LocationModel
import com.socialevoeding.domain.model.Place
import com.socialevoeding.domain.model.Result

interface PlaceRepository {
    suspend fun refreshPlaces(currentLocationModel : LocationModel, currentCategoryName : String, currentQueryNames : List<String>)
    suspend fun getPlacesFromLocalDatabase() : MutableList<Place>
    suspend fun insertPlacesIntoDatabase(places: List<Place>)
}