package com.socialevoeding.framework_androidsdk.remote.retrofit.apiServices

import com.socialevoeding.data.dtos.remote.NetworkPlace
import com.socialevoeding.framework_androidsdk.remote.retrofit.converters.PlacesRedirect
import com.socialevoeding.framework_androidsdk.remote.retrofit.converters.SearchRedirect
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlacesApiService {

    @GET("search?") @SearchRedirect
    fun getPlacesSearchRedirectUrlAsync(
        @Query("q") query: String?
    ): Deferred<String>

    @GET("{redirectUrl}") @PlacesRedirect
    fun getPlacesDetailsRedirectUrlAsync(
        @Path("redirectUrl") redirectUrl : String?
    ): Deferred<String>

    @GET("{redirectUrl}")
    fun getPlacesAsync(
        @Path("redirectUrl") redirectUrl: String?
    ): Deferred<List<NetworkPlace>>

    @GET("search?tbm=isch")
    fun getDetailsAsync(
        @Query("q") query: String?
    ): Deferred<List<NetworkPlace>>
}