package com.socialevoeding.data.repositories

import com.socialevoeding.data.dtos.remote.NetworkPlace
import com.socialevoeding.data.mappers.PlacePlaceMapper
import com.socialevoeding.data.mappers.NetworkPlaceMapper
import com.socialevoeding.data.mockSources.PlaceLocalDataSourceImplementation
import com.socialevoeding.data.mockSources.PlaceLocalDataSourceMock
import com.socialevoeding.data.mockSources.PlaceRemoteDataSourceMock
import com.socialevoeding.data.mockSources.PlaceRemoteDataSourceMockImplementation
import com.socialevoeding.data_entities.PlaceEntity
import com.socialevoeding.domain.model.place.Place
import com.socialevoeding.domain.model.UserLocation
import com.socialevoeding.util_datafactory.DataFactory
import com.socialevoeding.util_factories.PlaceEntityFactory
import com.socialevoeding.util_factories.PlaceFactory
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class PlaceRepositoryImplTest {
    private val placeLocalDataSourceImplementation = mockk<PlaceLocalDataSourceImplementation>(relaxed = true)
    private val placeLocalDataSourceMock = PlaceLocalDataSourceMock(placeLocalDataSourceImplementation)
    private val placeRemoteDataSourceMockImplementation = mockk<PlaceRemoteDataSourceMockImplementation>()
    private val placeRemoteDataSourceMock = PlaceRemoteDataSourceMock(placeRemoteDataSourceMockImplementation)
    private val placeName = DataFactory.randomString()
    private val queryName = DataFactory.randomString()
    private val categoryName = DataFactory.randomString()
    private val networkPlaces = listOf(
        NetworkPlace(DataFactory.randomString()),
        NetworkPlace(DataFactory.randomString()),
        NetworkPlace(DataFactory.randomString()),
        NetworkPlace(DataFactory.randomString()),
        NetworkPlace(DataFactory.randomString())
    )
    private val userLocation = mockk<UserLocation>()
    private val databasePlaceMapper = mockk<PlacePlaceMapper>()
    private val networkPlaceMapper = mockk<NetworkPlaceMapper>()

    private val placeRepositoryImpl = PlaceRepositoryImpl(
        placeRemoteDataSource = placeRemoteDataSourceMock,
        placeLocalDataSource = placeLocalDataSourceMock,
        databasePlaceMapper = databasePlaceMapper,
        networkPlaceMapper = networkPlaceMapper
    )

    private lateinit var places: List<Place>
    private lateinit var placeEntities: List<PlaceEntity>

    @Before
    fun setUp() {
        places = PlaceFactory.makePlacesList(5)
        placeEntities = PlaceEntityFactory.makePlaceEntitiesList(5)
    }

    @Test
    fun `refreshPlaces() calls insertAll(places) in local data source implementation`() = runBlockingTest {
        coEvery { userLocation.cityName } returns ""
        coEvery { placeRemoteDataSourceMockImplementation.getPlaces("", "") } returns networkPlaces
        coEvery { networkPlaceMapper.mapToListOfDomainObjects(networkPlaces) } returns places
        coEvery { databasePlaceMapper.mapFromEntities(placeEntities) } returns places
        coEvery { databasePlaceMapper.mapToEntities(places) } returns placeEntities

        placeRepositoryImpl.refreshPlaces(userLocation, "")

        coVerify { placeLocalDataSourceMock.insertAll(placeEntities) }
        coVerify { placeLocalDataSourceImplementation.insertAll(placeEntities) }
    }

    @Test
    fun `refreshPlaces() calls clear() in local data source implementation`() = runBlockingTest {
        coEvery { userLocation.cityName } returns ""
        coEvery { placeRemoteDataSourceMockImplementation.getPlaces("", "") } returns networkPlaces
        coEvery { networkPlaceMapper.mapToListOfDomainObjects(networkPlaces) } returns places
        coEvery { databasePlaceMapper.mapFromEntities(placeEntities) } returns places
        coEvery { databasePlaceMapper.mapToEntities(places) } returns placeEntities

        placeRepositoryImpl.refreshPlaces(userLocation, "")

        coVerify { placeLocalDataSourceMock.clear() }
        coVerify { placeLocalDataSourceImplementation.clear() }
    }

    @Test
    fun `refreshPlaces() calls getPlaces() in remote data source implementation`() = runBlockingTest {
        coEvery { userLocation.cityName } returns ""
        coEvery { databasePlaceMapper.mapFromEntities(placeEntities) } returns places
        coEvery { databasePlaceMapper.mapToEntities(places) } returns placeEntities
        coEvery { networkPlaceMapper.mapToListOfDomainObjects(networkPlaces) } returns places
        coEvery { placeRemoteDataSourceMockImplementation.getPlaces("", "") } returns networkPlaces

        placeRepositoryImpl.refreshPlaces(userLocation, "")

        coVerify { placeRemoteDataSourceMock.getPlaces("", "") }
        coVerify { placeRemoteDataSourceMockImplementation.getPlaces("", "") }
    }

    @Test
    fun `getPlacesFromDatabase calls local data source implementation`() = runBlockingTest {
        coEvery { placeRepositoryImpl.refreshPlaces(userLocation, categoryName) } returns Unit
        coEvery { placeLocalDataSourceImplementation.getPlaces() } returns placeEntities

        placeRepositoryImpl.getPlacesFromLocalDatabase()

        coVerify { placeLocalDataSourceMock.getPlaces() }
        coVerify { placeLocalDataSourceImplementation.getPlaces() }
    }

    @Test
    fun `insert places into local database is called`() = runBlockingTest {
        coEvery { networkPlaceMapper.mapToListOfDomainObjects(networkPlaces) } returns places
        coEvery { databasePlaceMapper.mapToEntities(places) } returns placeEntities

        placeRepositoryImpl.insertPlacesIntoDatabase(places)

        coVerify { placeLocalDataSourceMock.insertAll(placeEntities) }
        coVerify { placeLocalDataSourceImplementation.insertAll(placeEntities) }
    }
}