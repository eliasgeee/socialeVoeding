package com.socialevoeding.bap.domain.repositories

import com.socialevoeding.bap.domain.model.LocationModel
import com.socialevoeding.bap.domain.model.Place

interface PlaceRepository {
    suspend fun refreshPlaces(currentLocationModel : LocationModel, currentCategoryName : String, currentQueryNames : List<String>) : Boolean
    suspend fun getPlacesFromLocalDatabase() : MutableList<Place>
    suspend fun insertPlacesIntoDatabase(places: ArrayList<Place>): Boolean
}