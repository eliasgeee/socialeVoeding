package com.socialevoeding.data.mockSources

import com.socialevoeding.data.datasources.local.database.PlaceLocalDataSource
import com.socialevoeding.data_entities.PlaceEntity

class PlaceLocalDataSourceMock(private val placeLocalDataSourceImplementation: PlaceLocalDataSourceImplementation) : PlaceLocalDataSource {
    override suspend fun insertPlaces(places: List<PlaceEntity>) {
        placeLocalDataSourceImplementation.insertPlaces(places)
    }

    override suspend fun getPlaces(): List<PlaceEntity> {
        return placeLocalDataSourceImplementation.getPlaces()
    }

    override suspend fun insertAll(places: List<PlaceEntity>) {
        placeLocalDataSourceImplementation.insertAll(places)
    }

    override suspend fun clear() {
        placeLocalDataSourceImplementation.clear()
    }
}

class PlaceLocalDataSourceImplementation() {

    var cachedPlaces = emptyList<PlaceEntity>()

    init {
    cachedPlaces = emptyList()
    }

    suspend fun insertPlaces(places: List<PlaceEntity>) {
        cachedPlaces = places
    }

    suspend fun getPlaces(): List<PlaceEntity> {
        return cachedPlaces
    }

    suspend fun insertAll(places: List<PlaceEntity>) {
        cachedPlaces = places
    }

    suspend fun clear() {
        cachedPlaces = emptyList()
    }
}