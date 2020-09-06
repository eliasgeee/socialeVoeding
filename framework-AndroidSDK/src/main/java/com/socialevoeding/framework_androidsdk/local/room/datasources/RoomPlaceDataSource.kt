package com.socialevoeding.framework_androidsdk.local.room.datasources

import com.socialevoeding.data.datasources.local.database.PlaceLocalDataSource
import com.socialevoeding.data_entities.PlaceEntity
import com.socialevoeding.framework_androidsdk.local.room.dao.PlaceDao
import com.socialevoeding.framework_androidsdk.local.room.mappers.RoomPlaceMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RoomPlaceDataSource(private val placeDao: PlaceDao) : PlaceLocalDataSource {
    override suspend fun insertPlaces(places: List<PlaceEntity>) = placeDao.insertPlaces(RoomPlaceMapper.mapToRoomEntities(places))
    override suspend fun getPlaces(): Flow<List<PlaceEntity>> =
        flow { val place = placeDao.getPlaces().map { RoomPlaceMapper.mapFromRoomEntity(it) }
        emit(place)}
    override suspend fun insertAll(places: List<PlaceEntity>) = placeDao.insertAll(*RoomPlaceMapper.mapToRoomEntities(places).toTypedArray())
    override suspend fun clear() = placeDao.clear()
}