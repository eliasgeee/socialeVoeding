package com.socialevoeding.data.mappers

import com.socialevoeding.data.dtos.local.device.UserLocationDTO
import com.socialevoeding.data.mappers.base.CacheMapper
import com.socialevoeding.domain.model.Either
import com.socialevoeding.domain.model.UserLocation

object CacheLocationItemMapper : CacheMapper<Either.Right<UserLocationDTO>, Either.Right<UserLocation>> {
    override fun mapToDomainObject(cacheItem: Either.Right<UserLocationDTO>): Either.Right<UserLocation> {
        return Either.Right(
            UserLocation(
                latitude = cacheItem.b.latitude,
                longitude = cacheItem.b.longitude,
                cityName = cacheItem.b.cityName
            )
        )
    }
}