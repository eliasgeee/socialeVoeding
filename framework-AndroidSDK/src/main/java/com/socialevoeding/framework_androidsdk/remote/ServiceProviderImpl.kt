package com.socialevoeding.framework_androidsdk.remote

import com.socialevoeding.framework_androidsdk.remote.retrofit.apiServices.GeoLocationApiService

class ServiceProviderImpl(private val serviceFactory: ServiceFactory) : ServiceProvider {
    override fun getGeolocationService(): GeoLocationApiService {
        return serviceFactory.create(GeoLocationApiService::class.java)
    }
}
