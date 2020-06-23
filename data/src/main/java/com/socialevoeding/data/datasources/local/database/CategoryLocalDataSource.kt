package com.socialevoeding.data.datasources.local.database

import com.socialevoeding.data.dtos.local.database.CategoryEntity

interface CategoryLocalDataSource {
    fun getCategories() : List<CategoryEntity>
    fun insert(categories : List<CategoryEntity>)
}