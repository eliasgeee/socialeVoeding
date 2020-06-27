package com.socialevoeding.data.repositories

import com.socialevoeding.data.datasources.device.CurrentLocationDataSource
import com.socialevoeding.data.datasources.local.cache.UserLocationCacheDataSource
import com.socialevoeding.data.datasources.remote.UserLocationRemoteDataSource
import com.socialevoeding.data.mappers.CacheLocationItemMapper
import com.socialevoeding.data.mappers.DeviceCoordinatesMapper
import com.socialevoeding.data.utils.createUserLocation
import com.socialevoeding.domain.model.Coordinates
import com.socialevoeding.util_models.Either
import com.socialevoeding.domain.model.UserLocation
import com.socialevoeding.domain.repositories.UserLocationRepository

class UserLocationRepositoryImpl(
    private val userLocationRemoteDataSource: UserLocationRemoteDataSource,
    private val userLocationCacheDataSource: UserLocationCacheDataSource,
    private val currentLocationDataSource: CurrentLocationDataSource,
    private val cacheLocationItemMapper: CacheLocationItemMapper,
    private val deviceCoordinatesMapper: DeviceCoordinatesMapper
) : UserLocationRepository {

    override suspend fun getCurrentGeoLocation(currentCoordinates: Coordinates): UserLocation {
        return userLocationRemoteDataSource.getCurrentGeoLocationAsync(
            latitude = currentCoordinates.latitude,
            longitude = currentCoordinates.longitude
        ).await().createUserLocation(currentCoordinates)
    }

    override suspend fun getLastKnownUserLocation(): Either<Unit, UserLocation> {
        return when (val result = userLocationCacheDataSource.getLastKnownUserLocation()) {
            is Either.Right -> cacheLocationItemMapper.mapToDomainObject(result)
            is Either.Left -> result
        }
    }

    override suspend fun getCurrentUserCoordinates(): Coordinates {
        return deviceCoordinatesMapper.mapToDomainObject(currentLocationDataSource.getCurrentLocation())
    }
}
