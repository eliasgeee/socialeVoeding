package com.socialevoeding.usecases.placeUseCases

import com.socialevoeding.domain.model.place.Place
import com.socialevoeding.domain.model.UserLocation
import com.socialevoeding.domain.repositories.PlaceRepository
import com.socialevoeding.usecases.base.UseCase
import com.socialevoeding.util_datafactory.DataFactory
import com.socialevoeding.util_factories.PlaceFactory
import com.socialevoeding.util_models.Result
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
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
    private val userLocation = UserLocation(420.0, 420.0, "Gent")

    @Before
    fun setUp() {
        refreshPlacesUseCase = RefreshPlacesUseCase(placeRepository)
        Dispatchers.setMain(testCoroutineDispatcher)
        places = PlaceFactory.makePlacesList(5)
    }

    @Test
    fun `refresh places use case calls place repository`() = testCoroutineDispatcher.runBlockingTest {
        refreshPlacesUseCase.currentCategorieName = name
        refreshPlacesUseCase.userLocation = userLocation

        coEvery { placeRepository.refreshPlaces(userLocation, name) } returns Unit

        refreshPlacesUseCase.execute { }

        advanceTimeBy(1_000)

        coVerify { placeRepository.refreshPlaces(userLocation, name) }
    }

    @Test
    fun `refresh places use case returns success from place repository`() = testCoroutineDispatcher.runBlockingTest {
        refreshPlacesUseCase.currentCategorieName = name
        refreshPlacesUseCase.userLocation = userLocation

        coEvery { placeRepository.refreshPlaces(userLocation, name) } returns Unit

        var result : Result<Unit>? = null

        pauseDispatcher {
            refreshPlacesUseCase.execute {
                onComplete {
                    result = it
                } }

            runCurrent()
            advanceTimeBy(2_000)
        }

        delay(5_000)
        Assert.assertEquals(Result.Success(Unit), result)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }
}