package com.socialevoeding.data.mappers

import com.socialevoeding.data.dtos.remote.NetworkPlace
import com.socialevoeding.data.mappers.base.NetworkMapper
import com.socialevoeding.domain.model.Place
import com.socialevoeding.util_factories.PlaceFactory

object NetworkPlaceMapper :
    NetworkMapper<NetworkPlace, Place> {
    override fun mapToDomainObject(networkObject: NetworkPlace): Place {
        return PlaceFactory.makePlace()
    }

    override fun mapToListOfDomainObjects(entities: List<NetworkPlace>): List<Place> {
        return PlaceFactory.makePlacesList()
    }
}
