package com.socialevoeding.usecases.locationUseCases

import com.socialevoeding.domain.model.Coordinates
import com.socialevoeding.domain.repositories.UserLocationRepository
import com.socialevoeding.usecases.locationUseCases.GetCurrentCoordinatesUseCase
import io.mockk.MockKAnnotations
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
class GetCurrentCoordinatesUseCaseTest {
    private lateinit var getCurrentCoordinatesUseCase: GetCurrentCoordinatesUseCase
    private val userLocationRepository = mockk<UserLocationRepository>()
    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val coordinates = Coordinates(420.0, 69.69)

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getCurrentCoordinatesUseCase = GetCurrentCoordinatesUseCase(userLocationRepository)
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @Test
    fun getcurrentusercoordinatesusecasecallsuserlocationrepository() = runBlocking {
        coEvery { userLocationRepository.getCurrentUserCoordinates() } returns coordinates

        testCoroutineDispatcher.runBlockingTest {

            getCurrentCoordinatesUseCase.execute {}
            coVerify { userLocationRepository.getCurrentUserCoordinates() }
        }
    }

    @Test
    fun getcurrentusercoordinatesusecasereturnscoordinatesuser() = runBlocking {
        coEvery { userLocationRepository.getCurrentUserCoordinates() } returns coordinates

        var responseCoordinates: Coordinates? = null

        testCoroutineDispatcher.runBlockingTest {
            getCurrentCoordinatesUseCase.execute {
                onComplete {
                    responseCoordinates = it.data
                }
            }
        }
        runBlockingTest {
            delay(10000)
        }
        Assert.assertEquals(coordinates, responseCoordinates)
    }
}
