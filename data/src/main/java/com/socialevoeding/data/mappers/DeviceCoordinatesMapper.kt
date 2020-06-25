package com.socialevoeding.data.mappers

import com.socialevoeding.data.dtos.local.device.CoordinatesDTO
import com.socialevoeding.data.mappers.base.CacheMapper
import com.socialevoeding.domain.model.Coordinates

object DeviceCoordinatesMapper : CacheMapper<CoordinatesDTO, Coordinates> {
    override fun mapToDomainObject(cacheItem: CoordinatesDTO): Coordinates {
        return Coordinates(
            latitude = cacheItem.latitude,
            longitude = cacheItem.longitude
        )
    }
}