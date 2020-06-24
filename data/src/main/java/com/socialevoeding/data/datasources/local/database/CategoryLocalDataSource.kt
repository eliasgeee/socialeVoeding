package com.socialevoeding.data.datasources.local.database

import com.socialevoeding.data.dtos.local.database.CategoryEntity

interface CategoryLocalDataSource {
    suspend fun getCategories(): List<CategoryEntity>
    suspend fun insert(categories: List<CategoryEntity>)
}