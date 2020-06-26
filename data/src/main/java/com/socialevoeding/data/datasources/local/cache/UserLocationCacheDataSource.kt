package com.socialevoeding.data.datasources.local.cache

import com.socialevoeding.data.dtos.local.device.UserLocationDTO
import com.socialevoeding.li.models.Either

interface UserLocationCacheDataSource {
    fun storeLastKnownUserLocation(currentLocation: UserLocationDTO)
    fun getLastKnownUserLocation(): Either<Unit, UserLocationDTO>
}