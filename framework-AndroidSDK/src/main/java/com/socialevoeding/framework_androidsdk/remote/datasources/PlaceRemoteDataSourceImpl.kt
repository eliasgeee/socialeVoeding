package com.socialevoeding.framework_androidsdk.remote.datasources

import com.socialevoeding.data.dtos.remote.NetworkPlace
import com.socialevoeding.framework_androidsdk.remote.retrofit.creation.ServiceProviderImpl
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge

class PlaceRemoteDataSourceImpl(private val serviceProviderImpl: ServiceProviderImpl) {
    @FlowPreview
    suspend fun getPlaces(
        queryString: String,
        currenPlaceName: String
    ): Flow<List<NetworkPlace>> {
        return serviceProviderImpl.getPlacesService()
            .getRedirectUrlAsync("https://www.google.com/search?q=" + "voedselbank+gent")
            .flatMapMerge { serviceProviderImpl.getPlacesService()
                .getPlacesAsync("https://www.google.com$it")
            }
        //   var redirectUrl = responseRedirect.string()
        //     val string = serviceProviderImpl.getPlacesService().getPlacesAsync("https://www.google.com" + responseRedirect).string()
    }
}