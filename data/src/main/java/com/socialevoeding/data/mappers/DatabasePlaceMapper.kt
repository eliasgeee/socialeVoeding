package com.socialevoeding.data.mappers

import com.socialevoeding.data_entities.PlaceEntity
import com.socialevoeding.data.mappers.base.DatabaseMapperFacade
import com.socialevoeding.domain.model.Place
import com.socialevoeding.util_factories.PlaceEntityFactory
import com.socialevoeding.util_factories.PlaceFactory

object DatabasePlaceMapper : DatabaseMapperFacade<PlaceEntity, Place> {
    override fun mapFromEntity(entity: PlaceEntity): Place {
        return PlaceFactory.makePlace()
    }

    override fun mapToEntity(model: Place): PlaceEntity {
        return PlaceEntityFactory.makePlaceEntity()
    }

    override fun mapFromEntities(entities: List<PlaceEntity>): List<Place> {
        return entities.map { mapFromEntity(it) }
    }

    override fun mapToEntities(models: List<Place>): List<PlaceEntity> {
        return models.map { mapToEntity(it) }
    }
}