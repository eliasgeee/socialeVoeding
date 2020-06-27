package com.socialevoeding.usecases.locationUseCases

import com.socialevoeding.domain.model.Coordinates
import com.socialevoeding.domain.model.UserLocation
import com.socialevoeding.domain.repositories.UserLocationRepository
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
class GetCurrentGeoLocationUseCaseTest {
    private lateinit var getCurrentGeoLocationUseCase: GetCurrentGeoLocationUseCase
    private val userLocationRepository = mockk<UserLocationRepository>()
    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private lateinit var currentCoordinates: Coordinates
    private lateinit var userLocation: UserLocation

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getCurrentGeoLocationUseCase = GetCurrentGeoLocationUseCase(userLocationRepository)
        Dispatchers.setMain(testCoroutineDispatcher)
        currentCoordinates = Coordinates(420.0, 69.69)
        getCurrentGeoLocationUseCase.currentCoordinates = currentCoordinates
        userLocation = UserLocation(420.0, 69.69, "Gent")
    }

    @Test
    fun getcurrentgeolocationusecasecallsuserlocationrepository() = runBlocking {
        coEvery { userLocationRepository.getCurrentGeoLocation(currentCoordinates) } returns userLocation

        testCoroutineDispatcher.runBlockingTest {

            getCurrentGeoLocationUseCase.execute {}
            coVerify { userLocationRepository.getCurrentGeoLocation(currentCoordinates) }
        }
    }

    @Test
    fun getcurrentgeolocationreturnsuserlocationfromrepository() = runBlocking {
        coEvery { userLocationRepository.getCurrentGeoLocation(currentCoordinates) } returns userLocation

        var responseUserLocation: UserLocation? = null

        Thread.sleep(5000)
        testCoroutineDispatcher.runBlockingTest {
            getCurrentGeoLocationUseCase.execute {
                onComplete {
                    responseUserLocation = it.data
                }
            }
        }
        runBlockingTest {
            delay(10000)
        }
        Assert.assertEquals(userLocation, responseUserLocation)
    }
}
