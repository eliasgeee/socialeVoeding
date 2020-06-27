package com.socialevoeding.usecases.placeUseCases

import com.socialevoeding.domain.repositories.PlaceRepository
import com.socialevoeding.util_factories.PlaceFactory
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
    fun `get places use case calls place repository`() = runBlocking {
        coEvery { placeRepository.getPlacesFromLocalDatabase() } returns PlaceFactory.makePlacesList(3).toMutableList()

        testCoroutineDispatcher.runBlockingTest {
            getPlacesUseCase.execute { }
            coVerify { placeRepository.getPlacesFromLocalDatabase() }
        }
    }

    @Test
    fun `get places use case returns list of places from place repository`() = runBlocking {
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
