package com.socialevoeding.bap.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.socialevoeding.bap.data.entities.PlaceEntity

@Dao
interface PlaceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMockData(mockTaken: List<PlaceEntity>)

    @Query("select * from PlaceEntity")
    fun getPlaces(): List<PlaceEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg videos: PlaceEntity)
}