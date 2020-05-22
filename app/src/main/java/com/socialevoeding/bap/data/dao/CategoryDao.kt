package com.socialevoeding.bap.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.socialevoeding.bap.data.entities.CategoryEntity

@Dao
interface CategoryDao {
    @Query("select * from CategoryEntity")
    fun getCategories(): List<CategoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMockData(mockTaken: List<CategoryEntity>)
}