package com.socialevoeding.usecases.categoryUseCases

import com.socialevoeding.domain.model.Category
import com.socialevoeding.domain.repositories.CategoryRepository
import com.socialevoeding.usecases.categorieUseCases.InitCategoriesUseCase
import com.socialevoeding.util_models.Result
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
class InitCategoriesUseCaseTest {
    private lateinit var initCategoriesUseCase: InitCategoriesUseCase
    private val categoryRepository = mockk<CategoryRepository>()
    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private lateinit var categories: List<Category>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        initCategoriesUseCase = InitCategoriesUseCase(categoryRepository)
        Dispatchers.setMain(testCoroutineDispatcher)
        categories = CategoryFactory.makeCategoriesList(5)
        initCategoriesUseCase.categories = categories
    }

    @Test
    fun initcategoriesusecasecallscategoryrepository() = runBlocking {
        coEvery { categoryRepository.insertCategoriesIntoDatabase(categories) } returns Unit

        testCoroutineDispatcher.runBlockingTest {

            initCategoriesUseCase.execute {}
            coVerify { categoryRepository.insertCategoriesIntoDatabase(categories) }
        }
    }

    @Test
    fun `init categories returns succes from repository`() = runBlocking {
        coEvery { categoryRepository.insertCategoriesIntoDatabase(categories) } returns Unit

        var result = mockk<Result<Unit>>()
        Thread.sleep(5000)

        testCoroutineDispatcher.runBlockingTest {
            initCategoriesUseCase.execute {
                onComplete {
                    result = it
                }
            }
            }

        runBlockingTest {
            delay(5000)
        }

            Assert.assertEquals(Result.Success(Unit), result)
    }
}
