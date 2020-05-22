package com.socialevoeding.bap.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.socialevoeding.bap.data.CategoryDataProvider
import com.socialevoeding.bap.data.dao.CategoryDao
import com.socialevoeding.bap.data.entities.CategoryEntity
import com.socialevoeding.bap.data.entities.PlaceEntity
import com.socialevoeding.bap.data.entities.asDomainModel
import com.socialevoeding.bap.data.entities.toEntity
import com.socialevoeding.bap.domain.model.Category
import com.socialevoeding.bap.domain.repositories.CategoryRepository

class CategoryRepositoryImpl(private val categoryDao: CategoryDao) : CategoryRepository {

    override suspend fun getCategories(): MutableList<Category> {
        return categoryDao.getCategories().asDomainModel().toMutableList()
    }

    override suspend fun insertCategoriesIntoDatabase(categories : List<Category>): Boolean {
        categoryDao.insertMockData(CategoryDataProvider.getCategories())
        return true
    }
}