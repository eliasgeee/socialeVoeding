package com.socialevoeding.bap.restful.apiServices

import com.socialevoeding.bap.restful.SecretsManager
import com.socialevoeding.bap.restful.dataTransferObjects.PlaceDTO
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesApiService {

    @GET("search/")
    fun getPlacesAsync(
        @Query("") queryString: String?
    ): Deferred<PlaceDTO>
}