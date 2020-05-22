package com.socialevoeding.bap.domain.repositories

import com.socialevoeding.bap.domain.model.Place

interface PlaceRepository {
    suspend fun refreshPlaces() : Boolean
    suspend fun getPlacesFromLocalDatabase() : MutableList<Place>
    suspend fun insertPlacesIntoDatabase(places: ArrayList<Place>): Boolean
}