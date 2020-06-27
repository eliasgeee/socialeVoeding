package com.socialevoeding.domain.repositories

import com.socialevoeding.domain.model.Coordinates
import com.socialevoeding.domain.model.UserLocation
import com.socialevoeding.util_models.Either

interface UserLocationRepository {

    suspend fun getCurrentGeoLocation(currentCoordinates: Coordinates): UserLocation
    suspend fun getLastKnownUserLocation(): Either<Unit, UserLocation>
    suspend fun getCurrentUserCoordinates(): Coordinates
}