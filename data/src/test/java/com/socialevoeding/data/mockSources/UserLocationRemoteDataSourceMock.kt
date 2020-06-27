package com.socialevoeding.data.mockSources

import com.socialevoeding.data.datasources.remote.UserLocationRemoteDataSource
import com.socialevoeding.data.dtos.remote.GeolocationObject
import kotlinx.coroutines.Deferred

class UserLocationRemoteDataSourceMock(
    private val userLocationRemoteDataSourceMockImpl: UserLocationRemoteDataSourceMockImpl
) : UserLocationRemoteDataSource {
    override suspend fun getCurrentGeoLocationAsync(
        latitude: Double?,
        longitude: Double?,
        format: String
    ): Deferred<GeolocationObject> {
        return userLocationRemoteDataSourceMockImpl.getCurrentGeoLocationAsync(
            latitude,
            longitude,
            format
        )!!
    }
}

class UserLocationRemoteDataSourceMockImpl {
    suspend fun getCurrentGeoLocationAsync(
        latitude: Double?,
        longitude: Double?,
        format: String
    ): Deferred<GeolocationObject>? {
        return null
    }
}