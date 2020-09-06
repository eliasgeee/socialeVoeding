package com.socialevoeding.domain.repositories

import com.socialevoeding.domain.model.Category

interface CategoryRepository {
    suspend fun getCategories(): List<Category>
    suspend fun insertCategoriesIntoDatabase(category: List<Category>)
}