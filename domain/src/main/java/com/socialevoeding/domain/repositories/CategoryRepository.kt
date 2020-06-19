package com.socialevoeding.domain.repositories

import com.socialevoeding.domain.model.Category
import com.socialevoeding.domain.model.Result

interface CategoryRepository {
    suspend fun getCategories() : MutableList<Category>
    suspend fun insertCategoriesIntoDatabase(category: List<Category>)
}