package tests.categoryUseCases

import com.socialevoeding.domain.repositories.CategoryRepository
import com.socialevoeding.li.factory.CategoryFactory
import com.socialevoeding.usecases.categorieUseCases.GetCategoriesUseCase
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetCategoriesUseCaseTest {
    private lateinit var getCategoriesUseCase: GetCategoriesUseCase
    private val categoryRepository = mockk<CategoryRepository>()
    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getCategoriesUseCase = GetCategoriesUseCase(categoryRepository)
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @Test
    fun `get categories use case calls category repository`() = runBlocking {
        coEvery { categoryRepository.getCategories() } returns CategoryFactory.makeCategoriesList(2).toMutableList()

        testCoroutineDispatcher.runBlockingTest {

            getCategoriesUseCase.execute {}
            coVerify { categoryRepository.getCategories() }
        }
    }

    @Test
    fun `get categories returns categories from category repository`() = runBlocking {
        coEvery { categoryRepository.getCategories() } returns CategoryFactory.makeCategoriesList(2).toMutableList()

        var size = 0

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