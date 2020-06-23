package com.socialevoeding.data.mappers

import com.socialevoeding.data.dtos.local.database.PlaceEntity
import com.socialevoeding.data.mappers.base.DatabaseMapperFacade
import com.socialevoeding.domain.model.Place

object DatabasePlaceMapper : DatabaseMapperFacade<PlaceEntity, Place> {
    override fun mapFromEntity(entity: PlaceEntity): Place {
        TODO("Not yet implemented")
    }

    override fun mapToEntity(model: Place): PlaceEntity {
        TODO("Not yet implemented")
    }

    override fun mapFromEntities(entities: List<PlaceEntity>): List<Place> {
        return entities.map(DatabasePlaceMapper::mapFromEntity)
    }

    override fun mapToEntities(models: List<Place>): List<PlaceEntity> {
        return models.map(DatabasePlaceMapper::mapToEntity)
    }
}