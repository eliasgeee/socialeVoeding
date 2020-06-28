package com.socialevoeding.data.mappers.base

import com.socialevoeding.util_models.Either

interface NetworkMapper<NetworkObject, DomainObject> {
    fun mapToDomainObject(networkObject: NetworkObject): Either<Unit, DomainObject>
    fun mapToListOfDomainObjects(entities: List<NetworkObject>): List<DomainObject>
}