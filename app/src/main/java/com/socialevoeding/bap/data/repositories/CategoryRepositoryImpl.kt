package com.socialevoeding.bap.data.repositories

import com.socialevoeding.bap.data.CategoryDataProvider
import com.socialevoeding.bap.data.dao.CategoryDao
import com.socialevoeding.bap.data.entities.asDomainModel
import com.socialevoeding.domain.model.Category
import com.socialevoeding.domain.model.Result
import com.socialevoeding.domain.repositories.CategoryRepository

class CategoryRepositoryImpl(private val categoryDao: CategoryDao) :
    CategoryRepository {

    override suspend fun getCategories(): MutableList<Category> {
        return categoryDao.getCategories().asDomainModel().toMutableList()
    }

    override suspend fun insertCategoriesIntoDatabase(categories : List<Category>) {
        return categoryDao.insertMockData(CategoryDataProvider.getCategories())
    }
}