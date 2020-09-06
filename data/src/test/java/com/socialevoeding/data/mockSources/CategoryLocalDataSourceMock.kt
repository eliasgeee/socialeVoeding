package com.socialevoeding.data.mockSources

import com.socialevoeding.data.datasources.local.database.CategoryLocalDataSource
import com.socialevoeding.entities.CategoryEntity

class CategoryLocalDataSourceMock(private val categoryDatabaseSourceMock: CategoryDatabaseSourceMock) : CategoryLocalDataSource {
    override suspend fun getCategories(): List<CategoryEntity> {
        return categoryDatabaseSourceMock.getCategories()
    }

    override suspend fun insert(categories: List<CategoryEntity>) {
        categoryDatabaseSourceMock.insert(categories)
    }
}

class CategoryDatabaseSourceMock {
    suspend fun insert(categories: List<CategoryEntity>) {
        // do something
    }

    suspend fun getCategories(): List<CategoryEntity> {
        return emptyList()
    }
}