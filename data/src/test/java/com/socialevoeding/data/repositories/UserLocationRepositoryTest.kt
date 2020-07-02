package com.socialevoeding.data.repositories

import com.socialevoeding.data.dtos.local.device.CoordinatesDTO
import com.socialevoeding.data.dtos.local.device.UserLocationDTO
import com.socialevoeding.data.dtos.remote.GeolocationObject
import com.socialevoeding.data.dtos.remote.Address
import com.socialevoeding.data.mappers.CacheLocationItemMapper
import com.socialevoeding.data.mappers.DeviceCoordinatesMapper
import com.socialevoeding.data.mockSources.*
import com.socialevoeding.data.utils.createUserLocation
import com.socialevoeding.domain.model.Coordinates
import com.socialevoeding.domain.model.UserLocation
import com.socialevoeding.util_models.Either
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class UserLocationRepositoryTest {
    private val userLocationRemoteDataSourceMockImpl = mockk<UserLocationRemoteDataSourceMockImpl>()
    private val userLocationRemoteDataSourceMock = UserLocationRemoteDataSourceMock(userLocationRemoteDataSourceMockImpl)
    private val userLocationCacheDataSourceMockImpl = mockk<UserLocationCacheDataSourceMockImpl>()
    private val userLocationCacheDataSourceMock = UserLocationCacheDataSourceMock(userLocationCacheDataSourceMockImpl)
    private val userLocationCurrentLocationDeviceDataSourceMockImpl = mockk<UserLocationCurrentLocationDeviceDataSourceMockImpl>()
    private val userLocationCurrentLocationDeviceDataSourceMock =
        UserLocationCurrentLocationDeviceDataSourceMock(userLocationCurrentLocationDeviceDataSourceMockImpl)
    private val cacheLocationItemMapper = mockk<CacheLocationItemMapper>()
    private val deviceCoordinatesMapper = mockk<DeviceCoordinatesMapper>()
    private val currentCoordinates = Coordinates(420.0, 420.0)
    private val geolocationObject = mockk<GeolocationObject>()
    private val networkGeolocationAddress = mockk<Address>()
    private val userLocationDTO = mockk<UserLocationDTO>()
    private val userLocation = mockk<UserLocation>()
    private val coordinatesDTO = mockk<CoordinatesDTO>()
    private val coordinates = mockk<Coordinates>()

    private val userLocationRepositoryImpl = UserLocationRepositoryImpl(
        userLocationRemoteDataSourceMock,
        userLocationCacheDataSourceMock,
        userLocationCurrentLocationDeviceDataSourceMock,
        cacheLocationItemMapper,
        deviceCoordinatesMapper
    )

    @Test
    @Suppress("DeferredResultUnused")
    fun `getCurrentGeolocation calls remote data source`() = runBlockingTest {
        val deferred = CompletableDeferred<GeolocationObject>()

        coEvery { userLocationRemoteDataSourceMockImpl.getCurrentGeoLocationAsync(
            latitude = currentCoordinates.latitude,
            longitude = currentCoordinates.longitude,
            format = "json"
        ) } returns deferred

        coEvery { geolocationObject.address } returns networkGeolocationAddress
        coEvery { networkGeolocationAddress.city } returns "Gent"

        deferred.complete(geolocationObject)

        userLocationRepositoryImpl.getCurrentGeoLocation(currentCoordinates)

        coVerify {
            userLocationRemoteDataSourceMock.getCurrentGeoLocationAsync(
                latitude = currentCoordinates.latitude,
                longitude = currentCoordinates.longitude
            )
        }

        coVerify {
            userLocationRemoteDataSourceMockImpl.getCurrentGeoLocationAsync(
                latitude = currentCoordinates.latitude,
                longitude = currentCoordinates.longitude,
                format = "json"
            )
        }
    }

    @Test
    fun `user location gets created`() = runBlockingTest {
        val deferred = CompletableDeferred<GeolocationObject>()

        coEvery { geolocationObject.address } returns networkGeolocationAddress
        coEvery { networkGeolocationAddress.city } returns "Gent"

        coEvery {
            userLocationRemoteDataSourceMock.getCurrentGeoLocationAsync(
                latitude = currentCoordinates.latitude,
                longitude = currentCoordinates.longitude,
                format = "json"
            )
        } returns deferred

        deferred.complete(geolocationObject)

        val location = geolocationObject.createUserLocation(currentCoordinates)

        Assert.assertEquals(currentCoordinates.latitude, location.latitude, 0.0)
        Assert.assertEquals(currentCoordinates.longitude, location.longitude, 0.0)
        Assert.assertEquals("Gent", location.cityName)
    }

    @Test
    fun `getLastKnownUserLocation when a location is already cached`() = runBlockingTest {
        coEvery {
            userLocationCacheDataSourceMockImpl.getLastKnownUserLocation()
        } returns Either.Right(userLocationDTO)
        coEvery {
            cacheLocationItemMapper.mapToDomainObject(Either.Right(userLocationDTO))
        } returns Either.Right(userLocation)

        userLocationRepositoryImpl.getLastKnownUserLocation()

        coVerify { userLocationCacheDataSourceMock.getLastKnownUserLocation() }
        coVerify { userLocationCacheDataSourceMockImpl.getLastKnownUserLocation() }
    }

    @Test
    fun `getLastKnownLocation when no location is cached (first time log-in)`() = runBlockingTest {
        coEvery {
            userLocationCacheDataSourceMockImpl.getLastKnownUserLocation()
        } returns Either.Left(Unit)

        userLocationRepositoryImpl.getLastKnownUserLocation()

        coVerify { userLocationCacheDataSourceMock.getLastKnownUserLocation() }
        coVerify { userLocationCacheDataSourceMockImpl.getLastKnownUserLocation() }
    }

    @Test
    fun `getLastKnownLocation returns Success`() = runBlockingTest {
        coEvery {
            userLocationCacheDataSourceMockImpl.getLastKnownUserLocation()
        } returns Either.Right(userLocationDTO)
        coEvery {
            cacheLocationItemMapper.mapToDomainObject(Either.Right(userLocationDTO))
        } returns Either.Right(userLocation)

        userLocationRepositoryImpl.getLastKnownUserLocation()

        Assert.assertEquals(Either.Right(userLocation), userLocationRepositoryImpl.getLastKnownUserLocation())
    }

    @Test
    fun `getLastKnownLocation returns Unit`() = runBlockingTest {
        coEvery {
            userLocationCacheDataSourceMockImpl.getLastKnownUserLocation()
        } returns Either.Left(Unit)

        userLocationRepositoryImpl.getLastKnownUserLocation()

        Assert.assertEquals(Either.Left(Unit), userLocationRepositoryImpl.getLastKnownUserLocation())
    }

    @Test
    fun `getCurrentUserCoordinates calls device data source`() = runBlockingTest {
        coEvery {
            userLocationCurrentLocationDeviceDataSourceMockImpl.getCurrentLocation()
        } returns coordinatesDTO
        coEvery {
            deviceCoordinatesMapper.mapToDomainObject(coordinatesDTO)
        } returns coordinates

        userLocationRepositoryImpl.getCurrentUserCoordinates()

        coVerify { userLocationCurrentLocationDeviceDataSourceMock.getCurrentLocation() }
        coVerify { userLocationCurrentLocationDeviceDataSourceMockImpl.getCurrentLocation() }
    }
}