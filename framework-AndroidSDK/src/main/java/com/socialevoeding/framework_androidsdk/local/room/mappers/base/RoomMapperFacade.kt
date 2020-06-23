package com.socialevoeding.framework_androidsdk.local.room.mappers.base

interface RoomMapperFacade<RoomEntity, DataEntity> {
    fun mapFromRoomEntity(entity: RoomEntity): DataEntity
    fun mapToRoomEntity(model: DataEntity): RoomEntity
    fun mapFromRoomEntities(entities: List<RoomEntity>): List<DataEntity>
    fun mapToRoomEntities(models: List<DataEntity>): List<RoomEntity>
}