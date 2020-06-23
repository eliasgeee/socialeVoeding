package com.socialevoeding.data.datasources.remote

import com.socialevoeding.data.dtos.remote.GeolocationObject
import kotlinx.coroutines.Deferred

interface UserLocationRemoteDataSource {
    fun getCurrentGeoLocationAsync(latitude : Double?, longitude : Double?, format : String = "json") : Deferred<GeolocationObject>
}