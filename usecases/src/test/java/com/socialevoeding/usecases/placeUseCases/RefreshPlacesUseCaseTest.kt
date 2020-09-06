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
    private val userLocation = UserLocation(420.0, 420.0, "Gent")

    @Before
    fun setUp() {
        refreshPlacesUseCase = RefreshPlacesUseCase(placeRepository)
        Dispatchers.setMain(testCoroutineDispatcher)
        places = PlaceFactory.makePlacesList(5)
    }

    @Test
    fun `refresh places use case calls place repository`() = runBlockingTest {
        refreshPlacesUseCase.currentCategoryName = name
        refreshPlacesUseCase.userLocation = userLocation

        coEvery { placeRepository.refreshPlaces(userLocation, name) } returns Unit

        delay(5000)

        refreshPlacesUseCase.execute { }
        coVerify { placeRepository.refreshPlaces(userLocation, name) }
    }

    @Test
    fun `refresh places use case returns succes from place repository`() = runBlocking {
        refreshPlacesUseCase.currentCategoryName = name
        refreshPlacesUseCase.userLocation = userLocation

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