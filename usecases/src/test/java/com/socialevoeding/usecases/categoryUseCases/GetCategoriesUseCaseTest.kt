package com.socialevoeding.usecases.categoryUseCases

import com.socialevoeding.domain.repositories.CategoryRepository
import com.socialevoeding.usecases.categorieUseCases.GetCategoriesUseCase
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import com.socialevoeding.util_factories.CategoryFactory

@ExperimentalCoroutinesApi
class GetCategoriesUseCaseTest {
    private lateinit var getCategoriesUseCase: GetCategoriesUseCase
    private val categoryRepository = mockk<CategoryRepository>()
    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        getCategoriesUseCase = GetCategoriesUseCase(categoryRepository)
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @Test
    fun getcategoriesusecasecallscategoryrepository() = runBlocking {
        coEvery { categoryRepository.getCategories() } returns CategoryFactory.makeCategoriesList(2).toMutableList()

        testCoroutineDispatcher.runBlockingTest {

            getCategoriesUseCase.execute {}
            coVerify { categoryRepository.getCategories() }
        }
    }

    @Test
    fun getcategoriesreturnscategoriesfromcategoryrepository() = runBlocking {
        coEvery { categoryRepository.getCategories() } returns CategoryFactory.makeCategoriesList(2).toMutableList()

        var size = 0

        Thread.sleep(5000)
        testCoroutineDispatcher.runBlockingTest {
            getCategoriesUseCase.execute {
                onComplete {
                    size = it.data.size
                }
            }
        }

        runBlockingTest {
            delay(10000)
        }

        Assert.assertEquals(2, size)
    }
}
