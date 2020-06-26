package com.socialevoeding.data.repositories

import com.socialevoeding.data.datasources.device.CurrentLocationDataSource
import com.socialevoeding.data.datasources.local.cache.UserLocationCacheDataSource
import com.socialevoeding.data.datasources.remote.UserLocationRemoteDataSource
import com.socialevoeding.data.mappers.CacheLocationItemMapper
import com.socialevoeding.data.mappers.DeviceCoordinatesMapper
import com.socialevoeding.domain.model.Coordinates
import com.socialevoeding.util_models.Either
import com.socialevoeding.domain.model.UserLocation
import com.socialevoeding.domain.repositories.UserLocationRepository

// TODO
class UserLocationRepositoryImpl(
    private val userLocationRemoteDataSource: UserLocationRemoteDataSource,
    private val userLocationCacheDataSource: UserLocationCacheDataSource,
    private val currentLocationDataSource: CurrentLocationDataSource
) :
    UserLocationRepository {
    override suspend fun getCurrentGeoLocation(currentCoordinates: Coordinates): UserLocation {
        val cityName = userLocationRemoteDataSource.getCurrentGeoLocationAsync(
            latitude = currentCoordinates.latitude,
            longitude = currentCoordinates.longitude
        ).await().networkGeolocationAddress.city
        return UserLocation(
            latitude = currentCoordinates.latitude,
            longitude = currentCoordinates.longitude,
            cityName = cityName
        )
    }

    override suspend fun getLastKnownUserLocation(): Either<Unit, UserLocation> {
        return when (val result = userLocationCacheDataSource.getLastKnownUserLocation()) {
            is Either.Right -> CacheLocationItemMapper.mapToDomainObject(result)
            is Either.Left -> result
        }
    }

    override suspend fun getCurrentUserCoordinates(): Coordinates {
        return DeviceCoordinatesMapper.mapToDomainObject(currentLocationDataSource.getCurrentLocation())
    }
}