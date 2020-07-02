package com.socialevoeding.framework_retrofit

import com.socialevoeding.data.datasources.remote.UserLocationRemoteDataSource

class RemoteReverseGeolocationSource(private val apiService: ReverseGeolocationApiService) : UserLocationRemoteDataSource{
    override suspend fun getCurrentGeoLocationAsync(
        latitude: Double?,
        longitude: Double?,
        format: String
    ): String {
        return apiService.getCurrentGeoLocationAsync(latitude, longitude, "json").await().address.city
    }
}