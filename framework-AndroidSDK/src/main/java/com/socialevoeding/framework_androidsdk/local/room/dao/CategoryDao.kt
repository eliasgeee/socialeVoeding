package com.socialevoeding.framework_androidsdk.local.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.socialevoeding.framework_androidsdk.local.room.entities.RoomCategoryEntity

@Dao
interface CategoryDao {
    @Query("select * from RoomCategoryEntity")
    fun getCategories(): List<RoomCategoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMockData(mockTaken: List<RoomCategoryEntity>)
}