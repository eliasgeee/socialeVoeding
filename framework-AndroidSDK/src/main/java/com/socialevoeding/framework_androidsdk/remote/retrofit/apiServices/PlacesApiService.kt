package com.socialevoeding.framework_androidsdk.remote.retrofit.apiServices

import com.socialevoeding.data.dtos.remote.NetworkPlace
import com.socialevoeding.framework_androidsdk.remote.retrofit.converters.Places
import com.socialevoeding.framework_androidsdk.remote.retrofit.converters.PlacesRedirect
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow
import retrofit2.http.*

interface PlacesApiService {

    @Streaming
    @PlacesRedirect @GET
    suspend fun getRedirectUrlAsync(
        @Url url: String?
    ): Flow<String>

    @Streaming
    @GET @Places
    suspend fun getPlacesAsync(
        // @Path("redirectUrl") redirectUrl: String?
        @Url redirectUrl: String?
    ): Flow<List<NetworkPlace>>

    @GET("search?tbm=isch")
    suspend fun getDetailsAsync(
        @Query("q") query: String?
    ): Deferred<List<NetworkPlace>>
}