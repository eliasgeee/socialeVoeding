package com.socialevoeding.data.mappers.base

import com.socialevoeding.util_models.Either

interface PlaceMapperFacade<Entity, DomainObject> {
    fun mapFromEntity(entity: Entity): Either<Unit, DomainObject>
    fun mapToEntity(model: DomainObject): Entity
    fun mapFromEntities(entities: List<Entity>): List<DomainObject>
    fun mapToEntities(models: List<DomainObject>): List<Entity>
}