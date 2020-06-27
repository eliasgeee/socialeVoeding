package com.socialevoeding.usecases.placeUseCases

import com.socialevoeding.domain.model.Place
import com.socialevoeding.domain.repositories.CategoryRepository
import com.socialevoeding.domain.repositories.PlaceRepository
import com.socialevoeding.usecases.categorieUseCases.GetCategoriesUseCase
import com.socialevoeding.util_factories.PlaceFactory
import com.socialevoeding.util_models.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetPlacesUseCaseTest {
    private lateinit var getPlacesUseCase: GetPlacesUseCase
    private val placeRepository = mockk<PlaceRepository>()
    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        getPlacesUseCase = GetPlacesUseCase(placeRepository)
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @Test
    fun getcategoriesusecasecallscategoryrepository() = runBlocking {
        coEvery { placeRepository.getPlacesFromLocalDatabase() } returns PlaceFactory.makePlacesList(3).toMutableList()

        testCoroutineDispatcher.runBlockingTest {
            getPlacesUseCase.execute {  }
            coVerify { placeRepository.getPlacesFromLocalDatabase() }
        }
    }

    @Test
    fun getcategoriesusereturnslistofplacesfromplacerepository() = runBlocking {
        coEvery { placeRepository.getPlacesFromLocalDatabase() } returns PlaceFactory.makePlacesList(3).toMutableList()

        var size = 0

        Thread.sleep(5000)

        testCoroutineDispatcher.runBlockingTest {
            getPlacesUseCase.execute {
                onComplete {
                    size = it.data.size
                }
            }
        }

        runBlockingTest {
            delay(10000)
        }

        Assert.assertEquals(3, size)
    }
}
