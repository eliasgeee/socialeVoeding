package com.socialevoeding.framework_retrofit

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ReverseGeolocationApiService {

    @GET("reverse?")
    fun getCurrentGeoLocationAsync(
        @Query("lat") latitude: Double?,
        @Query("lon") longitude: Double?,
        @Query("format") format: String = "json"
    ): Deferred<GeolocationObject>
}