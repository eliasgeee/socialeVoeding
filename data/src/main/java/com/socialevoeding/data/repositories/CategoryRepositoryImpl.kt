package com.socialevoeding.data.repositories

import com.socialevoeding.data.CategoryDataProvider
import com.socialevoeding.data.datasources.local.database.CategoryLocalDataSource
import com.socialevoeding.data.mappers.DatabaseCategoryMapperFacade
import com.socialevoeding.domain.model.Category
import com.socialevoeding.domain.repositories.CategoryRepository

class CategoryRepositoryImpl(
    private val CategoryLocalDataSource: CategoryLocalDataSource) :
    CategoryRepository {

    override suspend fun getCategories(): MutableList<Category>
            = DatabaseCategoryMapperFacade.mapFromEntities(CategoryLocalDataSource.getCategories()).toMutableList()

    override suspend fun insertCategoriesIntoDatabase(category : List<Category>)
            = CategoryLocalDataSource.insert(CategoryDataProvider.getCategories())
}