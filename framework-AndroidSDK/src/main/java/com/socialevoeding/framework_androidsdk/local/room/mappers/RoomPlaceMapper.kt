package com.socialevoeding.framework_androidsdk.local.room.mappers

import com.socialevoeding.data.dtos.local.database.PlaceEntity
import com.socialevoeding.framework_androidsdk.local.room.entities.RoomPlaceEntity
import com.socialevoeding.framework_androidsdk.local.room.mappers.base.RoomMapperFacade

object RoomPlaceMapper : RoomMapperFacade<RoomPlaceEntity, PlaceEntity> {
    override fun mapFromRoomEntity(entity: RoomPlaceEntity): PlaceEntity {
        return PlaceEntity(
            name = entity.name,
            telephoneNumber = entity.telephoneNumber,
            address = entity.address,
            webUrl = entity.webUrl,
            img = entity.img,
            latitude = entity.latitude,
            longitude = entity.longitude,
            city = entity.city,
            categoryId = entity.categoryId
        )
    }

    override fun mapToRoomEntity(model: PlaceEntity): RoomPlaceEntity {
        return RoomPlaceEntity(
            name = model.name,
            telephoneNumber = model.telephoneNumber,
            address = model.address,
            webUrl = model.webUrl,
            img = model.img,
            latitude = model.latitude,
            longitude = model.longitude,
            city = model.city,
            categoryId = model.categoryId
        )
    }

    override fun mapFromRoomEntities(entities: List<RoomPlaceEntity>): List<PlaceEntity> {
        return entities.map(RoomPlaceMapper::mapFromRoomEntity)
    }

    override fun mapToRoomEntities(models: List<PlaceEntity>): List<RoomPlaceEntity> {
        return models.map(RoomPlaceMapper::mapToRoomEntity)
    }
}