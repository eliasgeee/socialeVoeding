package com.socialevoeding.framework_androidsdk.remote.datasources

import com.socialevoeding.data.datasources.remote.UserLocationRemoteDataSource
import com.socialevoeding.framework_androidsdk.remote.retrofit.creation.ServiceProviderImpl

class UserLocationRemoteDataSourceImpl(private val serviceProviderImpl: ServiceProviderImpl) : UserLocationRemoteDataSource {
    override suspend fun getCurrentGeoLocationAsync(
        latitude: Double?,
        longitude: Double?,
        format: String
    ): String {
        return serviceProviderImpl.getGeolocationService().getCurrentGeoLocationAsync(latitude, longitude, format).await().address.city
    }
}