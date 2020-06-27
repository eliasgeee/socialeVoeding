package com.socialevoeding.framework_androidsdk.local.room.mappers

import com.socialevoeding.data_entities.CategoryEntity
import com.socialevoeding.framework_androidsdk.local.room.entities.RoomCategoryEntity
import com.socialevoeding.framework_androidsdk.local.room.mappers.base.RoomMapperFacade

object RoomCategoryMapper : RoomMapperFacade<RoomCategoryEntity, CategoryEntity> {
    override fun mapFromRoomEntity(entity: RoomCategoryEntity): CategoryEntity {
        return CategoryEntity(
            id = entity.id,
            name = entity.name,
            type = entity.type
        )
    }

    override fun mapToRoomEntity(model: CategoryEntity): RoomCategoryEntity {
        return RoomCategoryEntity(
            id = model.id,
            name = model.name,
            type = model.type
        )
    }

    override fun mapFromRoomEntities(entities: List<RoomCategoryEntity>): List<CategoryEntity> {
        return entities.map(RoomCategoryMapper::mapFromRoomEntity)
    }

    override fun mapToRoomEntities(models: List<CategoryEntity>): List<RoomCategoryEntity> {
        return models.map(RoomCategoryMapper::mapToRoomEntity)
    }
}