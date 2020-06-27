package com.socialevoeding.usecases.placeUseCases

import com.socialevoeding.domain.model.Place
import com.socialevoeding.domain.model.UserLocation
import com.socialevoeding.domain.repositories.PlaceRepository
import com.socialevoeding.usecases.base.UseCase
import com.socialevoeding.util_factories.DataFactory
import com.socialevoeding.util_factories.PlaceFactory
import com.socialevoeding.util_models.Result
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class RefreshPlacesUseCaseTest {

    private lateinit var refreshPlacesUseCase: RefreshPlacesUseCase
    private val placeRepository = mockk<PlaceRepository>()
    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private lateinit var places: List<Place>
    private val name = DataFactory.randomString()
    private lateinit var userLocation: UserLocation

    @Before
    fun setUp() {
        refreshPlacesUseCase = RefreshPlacesUseCase(placeRepository)
        Dispatchers.setMain(testCoroutineDispatcher)
        places = PlaceFactory.makePlacesList(5)
        userLocation = mockk()
        refreshPlacesUseCase.currentCategorieName = name
        refreshPlacesUseCase.userLocation = userLocation
    }

    @Test
    fun refreshplaceusecasecallsplacerepository() = runBlocking {
        coEvery { placeRepository.refreshPlaces(userLocation, name) } returns Unit

        testCoroutineDispatcher.runBlockingTest {
            refreshPlacesUseCase.execute { }
            coVerify { placeRepository.refreshPlaces(userLocation, name) }
        }
    }

    @Test
    fun refreshplaceusereturnssuccesfromsplacerepository() = runBlocking {
        coEvery { placeRepository.refreshPlaces(userLocation, name) } returns Unit

        var result = mockk<Result<Unit>>()
        Thread.sleep(5000)

        val request = mockk<UseCase.Request<Unit>>()

        testCoroutineDispatcher.runBlockingTest {
            refreshPlacesUseCase.execute {
                onComplete {
                    result = it
                }
            }
        }

        val useCase = mockk<UseCase<Unit>>()

        Assert.assertEquals(Result.Success(Unit), result)
    }
}