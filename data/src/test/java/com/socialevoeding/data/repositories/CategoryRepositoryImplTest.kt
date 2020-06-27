package com.socialevoeding.data.repositories

import com.socialevoeding.data.CategoryDataProvider
import com.socialevoeding.data.mappers.DatabaseCategoryMapperFacade
import com.socialevoeding.data.mockSources.CategoryDatabaseSourceMock
import com.socialevoeding.data.mockSources.CategoryLocalDataSourceMock
import com.socialevoeding.data_entities.CategoryEntity
import com.socialevoeding.domain.model.Category
import com.socialevoeding.util_factories.CategoryEntityFactory
import com.socialevoeding.util_factories.CategoryFactory
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CategoryRepositoryImplTest {
    private val categoryLocalDataSourceImpl = mockk<CategoryDatabaseSourceMock>(relaxed = true)
    private val categoryLocalDataSource = CategoryLocalDataSourceMock(categoryLocalDataSourceImpl)
    private val categoryProvider = mockk<CategoryDataProvider>()
    private val categoryMapper = mockk<DatabaseCategoryMapperFacade>()
    private val categoryRepositoryImpl = CategoryRepositoryImpl(
        categoryLocalDataSource,
        categoryProvider,
        categoryMapper
    )
    private lateinit var categoryEntities: List<CategoryEntity>
    private lateinit var categories: List<Category>

    @Before
    fun setUp() {
        categoryEntities = CategoryEntityFactory.makeCategoryEntitiesList(5)
        categories = CategoryFactory.makeCategoriesList(5)
    }

    @Test
    fun `CategoryRepository getCategories() calls concrete data source implementation`() = runBlockingTest {
        coEvery { categoryLocalDataSourceImpl.getCategories() } returns categoryEntities
        coEvery { categoryMapper.mapFromEntities(categoryEntities) } returns categories

        categoryRepositoryImpl.getCategories()

        coVerify { categoryLocalDataSource.getCategories() }
        coVerify { categoryLocalDataSourceImpl.getCategories() }
    }

    @Test
    fun `CategoryRepository getCategories() returns categories`() = runBlockingTest {
        coEvery { categoryLocalDataSourceImpl.getCategories() } returns categoryEntities
        coEvery { categoryMapper.mapFromEntities(categoryEntities) } returns categories

        val categories = categoryRepositoryImpl.getCategories()

        Assert.assertEquals(5, categories.size)
    }

    @Test
    fun `CategoryRepository insertCategories() calls concrete data source implementation`() = runBlockingTest {
        coEvery { categoryRepositoryImpl.insertCategoriesIntoDatabase(categories) } returns Unit
        coEvery { categoryLocalDataSource.insert(mockMapCatDomainObjectToEntity(categories)) } returns Unit

        categoryLocalDataSource.insert(categoryEntities)

        coVerify { categoryLocalDataSourceImpl.insert(categoryEntities) }
    }

    private fun mockMapCatDomainObjectToEntity(categories: List<Category>): List<CategoryEntity> {
        return categoryEntities
    }
}