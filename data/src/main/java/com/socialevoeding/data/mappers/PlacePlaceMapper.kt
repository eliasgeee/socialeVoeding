package com.socialevoeding.data.mappers

import com.socialevoeding.data.mappers.base.PlaceMapperFacade
import com.socialevoeding.data_entities.PlaceEntity
import com.socialevoeding.domain.model.place.Place
import com.socialevoeding.domain.model.place.buildPlace
import com.socialevoeding.util_factories.PlaceEntityFactory
import com.socialevoeding.util_models.Either

object PlacePlaceMapper : PlaceMapperFacade<PlaceEntity, Place> {

    override fun mapFromEntity(entity: PlaceEntity): Either <Unit, Place> {
        return buildPlace {
            name = entity.name
            latitude = entity.latitude
            longitude = entity.longitude
            cityName = entity.city
            telephoneNumber = entity.telephoneNumber
            webUrl = entity.webUrl
            img = entity.img
            address = entity.address
        }
    }

    override fun mapToEntity(model: Place): PlaceEntity {
        return PlaceEntity(
            name = model.name,
            distance = 0,
            longitude = model.longitude,
            latitude = model.latitude,
            webUrl = model.webUrl!!,
            img = model.img!!,
            telephoneNumber = model.telephoneNumber!!,
            city = model.cityName,
            address = model.address!!
        )
    }

    override fun mapFromEntities(entities: List<PlaceEntity>): List<Place> {
        val models: List<Place> = emptyList()
        entities.forEach {
            when (val place = mapFromEntity(it)) {
                is Either.Right -> models.plus(place)
            }
        }
        return models
    }

    override fun mapToEntities(models: List<Place>): List<PlaceEntity> {
        return models.map { mapToEntity(it) }
    }
}