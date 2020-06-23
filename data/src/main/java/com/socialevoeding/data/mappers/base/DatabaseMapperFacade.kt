package com.socialevoeding.data.mappers.base

interface DatabaseMapperFacade<Entity, DomainObject> {
    fun mapFromEntity(entity: Entity): DomainObject
    fun mapToEntity(model: DomainObject): Entity
    fun mapFromEntities(entities: List<Entity>): List<DomainObject>
    fun mapToEntities(models: List<DomainObject>): List<Entity>
}