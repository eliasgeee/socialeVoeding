package com.socialevoeding.usecases.locationUseCases

import com.socialevoeding.domain.model.UserLocation
import com.socialevoeding.domain.repositories.UserLocationRepository
import com.socialevoeding.util_models.Either
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
class GetLastKnownUserLocationUseCaseTest {
    private lateinit var getLastKnownUserLocationUseCase: GetLastKnownUserLocationUseCase
    private val userLocationRepository = mockk<UserLocationRepository>()
    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val userLocation = UserLocation(420.0, 69.69, "Gent")

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getLastKnownUserLocationUseCase = GetLastKnownUserLocationUseCase(userLocationRepository)
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @Test
    fun `get last known user location use case calls user location repository`() = runBlocking {
        coEvery { userLocationRepository.getLastKnownUserLocation() } returns Either.Right(userLocation)

        testCoroutineDispatcher.runBlockingTest {
            getLastKnownUserLocationUseCase.execute { }
            coVerify { userLocationRepository.getLastKnownUserLocation() }
        }
    }

    @Test
    fun `get last known user location returns user location from repository`() = runBlocking {
        coEvery { userLocationRepository.getLastKnownUserLocation() } returns Either.Right(userLocation)

        var responseUserLocation: UserLocation? = null

        testCoroutineDispatcher.runBlockingTest {
            getLastKnownUserLocationUseCase.execute {
                onComplete {
                    responseUserLocation = when (it.data) {
                        is Either.Left -> null
                        is Either.Right -> (it.data as Either.Right<UserLocation>).b
                    }
                }
            }
            Thread.sleep(5000)
        }
        runBlockingTest {
            delay(20000000)
        }

        Assert.assertEquals(userLocation, responseUserLocation)
    }

    @Test
    fun `get last known user location returns unit from repository`() = runBlocking {
        coEvery { userLocationRepository.getLastKnownUserLocation() } returns Either.Left(Unit)

        var response: Either<Unit, UserLocation>? = null

        Thread.sleep(5000)
        testCoroutineDispatcher.runBlockingTest {
            getLastKnownUserLocationUseCase.execute {
                onComplete {
                    response = it.data
                }
            }
            delay(30000)
        }
        runBlockingTest {
            delay(10000)
        }
        Assert.assertEquals(Either.Left(Unit), response)
    }
}
