package com.socialevoeding.data.datasources.remote

import kotlinx.coroutines.Deferred

interface UserLocationRemoteDataSource {
    suspend fun getCurrentGeoLocationAsync(latitude: Double?, longitude: Double?, format: String = "json"): String
}