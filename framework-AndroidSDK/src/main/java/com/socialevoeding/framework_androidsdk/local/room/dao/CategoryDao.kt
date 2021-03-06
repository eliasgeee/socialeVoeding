package com.socialevoeding.framework_androidsdk.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.socialevoeding.framework_androidsdk.local.room.entities.RoomCategoryEntity

@Dao
interface CategoryDao {
    @Query("select * from categories")
    fun getCategories(): List<RoomCategoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMockData(mockTaken: List<RoomCategoryEntity>)
}