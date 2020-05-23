package com.socialevoeding.bap.restful.apiServices

import com.socialevoeding.bap.restful.dataTransferObjects.GeolocationObject
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoLocationApiService {

    @GET("reverse?")
    fun getCurrentGeoLocationAsync(
        @Query("lat") latidude : Double?,
        @Query("lon") longitude : Double?,
        @Query("format") format : String = "json"
    ) : Deferred<GeolocationObject>
}