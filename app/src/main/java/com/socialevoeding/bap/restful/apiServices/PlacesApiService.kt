package com.socialevoeding.bap.restful.apiServices

import com.socialevoeding.bap.restful.SecretsManager
import com.socialevoeding.bap.restful.dataTransferObjects.PlaceDTO
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesApiService {

    @GET("v2/venues/search?")
    fun getPlacesAsync(
        @Query("client_id") clientId: String? = SecretsManager.getClientId(),
        @Query("client_secret") clientSecret: String? = SecretsManager.getClientSecret(),
        @Query("v") version: String?,
        @Query("intent") intent: String?,
        @Query("limit") limit: Int?,
        @Query("ll") latitudeLongitude: String?,
        @Query("query") query: String?
    ): Deferred<PlaceDTO>
}