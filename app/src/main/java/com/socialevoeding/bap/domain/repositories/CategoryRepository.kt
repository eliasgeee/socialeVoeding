package com.socialevoeding.bap.domain.repositories

import com.socialevoeding.bap.domain.model.Category

interface CategoryRepository {
    suspend fun getCategories() : MutableList<Category>
    suspend fun insertCategoriesIntoDatabase(category: List<Category>): Boolean
}