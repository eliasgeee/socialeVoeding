package com.socialevoeding.usecases.placeUseCases

import com.socialevoeding.domain.model.place.Place
import com.socialevoeding.domain.repositories.PlaceRepository
import com.socialevoeding.util_factories.PlaceFactory
import com.socialevoeding.util_models.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
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
class SavePlacesUseCaseTest {
    private lateinit var savePlacesUseCase: SavePlacesUseCase
    private val placeRepository = mockk<PlaceRepository>()
    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private lateinit var places: List<Place>

    @Before
    fun setUp() {
        savePlacesUseCase = SavePlacesUseCase(placeRepository)
        Dispatchers.setMain(testCoroutineDispatcher)
        places = PlaceFactory.makePlacesList(5)
    }

    @Test
    fun `save places use case calls place repository`() = testCoroutineDispatcher.runBlockingTest {
        coEvery { placeRepository.insertPlacesIntoDatabase(emptyList()) } returns Unit

        savePlacesUseCase.execute { }
        advanceTimeBy(1_000)

        coVerify { placeRepository.insertPlacesIntoDatabase(emptyList()) }
    }

    @Test
    fun `save places use case returns succes`() = testCoroutineDispatcher.runBlockingTest {
        coEvery { placeRepository.insertPlacesIntoDatabase(emptyList()) } returns Unit

        var result: Result<Unit>? = null

        pauseDispatcher {
            savePlacesUseCase.execute {
                onComplete {
                    result = it
                }
            }

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