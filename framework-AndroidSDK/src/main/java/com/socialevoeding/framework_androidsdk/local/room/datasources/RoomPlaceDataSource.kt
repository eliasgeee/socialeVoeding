package com.socialevoeding.framework_androidsdk.local.room.datasources

import com.socialevoeding.data.datasources.local.database.PlaceLocalDataSource
import com.socialevoeding.data.dtos.local.database.PlaceEntity
import com.socialevoeding.framework_androidsdk.local.room.dao.PlaceDao
import com.socialevoeding.framework_androidsdk.local.room.mappers.RoomPlaceMapper

class RoomPlaceDataSource(private val placeDao: PlaceDao) : PlaceLocalDataSource {
    override suspend fun insertPlaces(places: List<PlaceEntity>) {
        placeDao.insertPlaces(RoomPlaceMapper.mapToRoomEntities(places))
    }

    override suspend fun getPlaces(): List<PlaceEntity> {
        return RoomPlaceMapper.mapFromRoomEntities(placeDao.getPlaces())
    }

    override suspend fun insertAll(places: List<PlaceEntity>) {
        placeDao.insertAll(*RoomPlaceMapper.mapToRoomEntities(places).toTypedArray())
    }

    override suspend fun clear() {
        placeDao.clear()
    }
}