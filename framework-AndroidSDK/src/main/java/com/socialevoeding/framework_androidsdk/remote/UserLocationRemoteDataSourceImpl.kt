package com.socialevoeding.framework_androidsdk.remote

import com.socialevoeding.data.datasources.remote.UserLocationRemoteDataSource

class UserLocationRemoteDataSourceImpl(private val serviceProviderImpl: ServiceProviderImpl) : UserLocationRemoteDataSource {
     override suspend fun getCurrentGeoLocationAsync(
        latitude: Double?,
        longitude: Double?,
        format: String
    ): String {
        return serviceProviderImpl.getGeolocationService().getCurrentGeoLocationAsync(latitude, longitude, format).await().address.city
    }
}