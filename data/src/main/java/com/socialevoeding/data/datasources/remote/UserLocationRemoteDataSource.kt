package com.socialevoeding.data.datasources.remote

interface UserLocationRemoteDataSource {
    suspend fun getCurrentGeoLocationAsync(latitude: Double?, longitude: Double?, format: String = "json"): String
}