package com.socialevoeding.domain.repositories

import com.socialevoeding.domain.model.Coordinates
import com.socialevoeding.li.models.Either
import com.socialevoeding.domain.model.UserLocation

interface UserLocationRepository {

    suspend fun getCurrentGeoLocation(currentCoordinates: Coordinates): UserLocation
    suspend fun getLastKnownUserLocation(): Either<Unit, UserLocation>
    suspend fun getCurrentUserCoordinates(): Coordinates
}