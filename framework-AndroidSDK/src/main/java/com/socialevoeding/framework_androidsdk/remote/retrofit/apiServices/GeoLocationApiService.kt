package com.socialevoeding.framework_androidsdk.remote.retrofit.apiServices

import com.socialevoeding.data.datasources.remote.UserLocationRemoteDataSource
import com.socialevoeding.data.dtos.remote.GeolocationObject
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoLocationApiService : UserLocationRemoteDataSource {

    @GET("reverse?")
    override suspend fun getCurrentGeoLocationAsync(
        @Query("lat") latitude: Double?,
        @Query("lon") longitude: Double?,
        @Query("format") format: String
    ): Deferred<GeolocationObject>
}