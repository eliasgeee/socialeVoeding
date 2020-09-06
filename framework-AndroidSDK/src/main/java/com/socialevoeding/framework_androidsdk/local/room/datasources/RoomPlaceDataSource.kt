package com.socialevoeding.framework_androidsdk.local.room.datasources

import com.socialevoeding.data.datasources.local.database.PlaceLocalDataSource
import com.socialevoeding.data_entities.PlaceEntity
import com.socialevoeding.framework_androidsdk.local.room.dao.PlaceDao
import com.socialevoeding.framework_androidsdk.local.room.mappers.RoomPlaceMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class RoomPlaceDataSource(private val placeDao: PlaceDao) : PlaceLocalDataSource {
    override suspend fun insertPlaces(places: List<PlaceEntity>) = placeDao.insertPlaces(RoomPlaceMapper.mapToRoomEntities(places))
    @ExperimentalCoroutinesApi
    override suspend fun getPlaces(): Flow<List<PlaceEntity>> =
        placeDao.getPlaces().distinctUntilChanged().map { entities -> RoomPlaceMapper.mapFromRoomEntities(entities) }
    override suspend fun insertAll(places: List<PlaceEntity>) = placeDao.insertAll(*RoomPlaceMapper.mapToRoomEntities(places).toTypedArray())
    override suspend fun clear() = placeDao.clear()
}