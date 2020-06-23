package com.socialevoeding.framework_androidsdk.local.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.socialevoeding.framework_androidsdk.local.room.entities.RoomPlaceEntity

@Dao
interface PlaceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlaces(places: List<RoomPlaceEntity>)

    @Query("select * from RoomPlaceEntity")
    fun getPlaces(): List<RoomPlaceEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg places: RoomPlaceEntity)
}