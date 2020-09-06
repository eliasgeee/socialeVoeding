package com.socialevoeding.framework_androidsdk.remote.retrofit.creation

import com.socialevoeding.framework_androidsdk.remote.retrofit.apiServices.GeoLocationApiService
import com.socialevoeding.framework_androidsdk.remote.retrofit.apiServices.PlacesApiService

class ServiceProviderImpl(private val serviceFactory: ServiceFactory) :
    ServiceProvider {
    override fun getGeolocationService(): GeoLocationApiService {
        return serviceFactory.create(GeoLocationApiService::class.java)
    }

    override fun getPlacesService(): PlacesApiService {
        return serviceFactory.create(PlacesApiService::class.java)
    }
}
