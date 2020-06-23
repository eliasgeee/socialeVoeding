package com.socialevoeding.data.repositories

import com.socialevoeding.data.datasources.remote.UserLocationRemoteDataSource
import com.socialevoeding.domain.model.PlaceLocation
import com.socialevoeding.domain.repositories.UserLocationRepository

//TODO
class UserLocationRepositoryImpl(private val userLocationRemoteDataSource: UserLocationRemoteDataSource) :
    UserLocationRepository {
    override suspend fun getCurrentGeoLocation(placeLocation: PlaceLocation) : PlaceLocation {
        val address = userLocationRemoteDataSource.getCurrentGeoLocationAsync(
            latitude = placeLocation.latitude,
            longitude = placeLocation.longitude
        ).await().networkGeolocationAddress.city
        placeLocation.cityName = address
        return placeLocation
    }
}