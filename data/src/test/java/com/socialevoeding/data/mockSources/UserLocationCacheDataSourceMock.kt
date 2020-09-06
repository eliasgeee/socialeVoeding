package com.socialevoeding.data.mockSources

import com.socialevoeding.data.datasources.local.cache.UserLocationCacheDataSource
import com.socialevoeding.data.dtos.local.device.UserLocationDTO
import com.socialevoeding.util_models.Either

class UserLocationCacheDataSourceMock(
    private val userLocationCacheDataSourceMockImpl: UserLocationCacheDataSourceMockImpl
) : UserLocationCacheDataSource {
    override fun storeLastKnownUserLocation(currentLocation: UserLocationDTO) {
        userLocationCacheDataSourceMockImpl.storeLastKnownUserLocation(currentLocation)
    }

    override fun getLastKnownUserLocation(): Either<Unit, UserLocationDTO> {
        return userLocationCacheDataSourceMockImpl.getLastKnownUserLocation()
    }
}

class UserLocationCacheDataSourceMockImpl {
    fun storeLastKnownUserLocation(currentLocation: UserLocationDTO) {
        // do something
    }

    fun getLastKnownUserLocation(): Either<Unit, UserLocationDTO> {
        return Either.Left(Unit)
    }
}