package com.socialevoeding.data.mappers

import com.socialevoeding.data.dtos.remote.NetworkPlace
import com.socialevoeding.data.mappers.base.NetworkMapper
import com.socialevoeding.domain.model.Place

object NetworkPlaceMapper :
    NetworkMapper<NetworkPlace, Place> {
    override fun mapToDomainObject(networkObject: NetworkPlace): Place {
        TODO("Not yet implemented")
    }

    override fun mapToListOfDomainObjects(entities: List<NetworkPlace>): List<Place> {
        TODO("Not yet implemented")
    }
}
