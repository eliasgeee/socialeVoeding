package tests.categoryUseCases

import com.socialevoeding.domain.model.Category
import com.socialevoeding.domain.repositories.CategoryRepository
import com.socialevoeding.li.factory.CategoryFactory
import com.socialevoeding.usecases.base.UseCase
import com.socialevoeding.usecases.categorieUseCases.InitCategoriesUseCase
import com.socialevoeding.util_models.Result
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class InitCategoriesUseCaseTest {
    private lateinit var initCategoriesUseCase: InitCategoriesUseCase
    private val categoryRepository = mockk<CategoryRepository>()
    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private lateinit var categories : List<Category>

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        initCategoriesUseCase = InitCategoriesUseCase(categoryRepository)
        Dispatchers.setMain(testCoroutineDispatcher)
        categories = CategoryFactory.makeCategoriesList(5)
        initCategoriesUseCase.categories = categories
    }

    @Test
    fun `init categories use case calls category repository`() = runBlocking {
        coEvery { categoryRepository.insertCategoriesIntoDatabase(categories) } returns Unit

        testCoroutineDispatcher.runBlockingTest {

            initCategoriesUseCase.execute {}
            coVerify { categoryRepository.insertCategoriesIntoDatabase(categories) }
        }
    }

    @Test
    fun `init categories returns succes from repository`() = runBlocking {
        coEvery { categoryRepository.insertCategoriesIntoDatabase(categories) } returns Unit

        var result : Result<Unit>? = null

        testCoroutineDispatcher.runBlockingTest {
            initCategoriesUseCase.execute {
                onComplete {
                    result = it
                }
            }
            }
        runBlockingTest {
            delay(10000)
        }
        Assert.assertEquals(Result.Success(Unit), result)
    }
}