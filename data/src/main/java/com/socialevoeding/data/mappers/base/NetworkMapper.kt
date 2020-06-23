package com.socialevoeding.data.mappers.base

interface NetworkMapper<NetworkObject, DomainObject> {
    fun mapToDomainObject(networkObject: NetworkObject): DomainObject
    fun mapToListOfDomainObjects(entities: List<NetworkObject>): List<DomainObject>
}