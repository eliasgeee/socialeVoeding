package com.socialevoeding.data.datasources.local.database

import com.socialevoeding.data_entities.PlaceEntity
import kotlinx.coroutines.flow.Flow

interface PlaceLocalDataSource {
    suspend fun insertPlaces(places: List<PlaceEntity>)
    suspend fun getPlaces(): Flow<List<PlaceEntity>>
    suspend fun insertAll(places: List<PlaceEntity>)
    suspend fun clear()
}