package com.socialevoeding.framework_androidsdk.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.socialevoeding.framework_androidsdk.local.room.entities.RoomPlaceEntity

@Dao
interface PlaceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaces(places: List<RoomPlaceEntity>)

    @Query("select * from RoomPlaceEntity")
    suspend fun getPlaces(): List<RoomPlaceEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg places: RoomPlaceEntity)

    @Query("DELETE FROM RoomPlaceEntity")
    suspend fun clear()
}