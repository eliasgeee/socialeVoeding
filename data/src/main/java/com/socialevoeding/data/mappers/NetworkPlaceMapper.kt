package com.socialevoeding.data.mappers

import com.socialevoeding.data.dtos.remote.NetworkPlace
import com.socialevoeding.data.mappers.base.NetworkMapper
import com.socialevoeding.domain.model.place.Place
import com.socialevoeding.util_factories.PlaceFactory
import com.socialevoeding.util_models.Either

// TODO mock objects
object NetworkPlaceMapper :
    NetworkMapper<NetworkPlace, Place> {
    override fun mapToDomainObject(networkObject: NetworkPlace): Either<Unit, Place> {
        return PlaceFactory.makePlace()
    }

    override fun mapToListOfDomainObjects(entities: List<NetworkPlace>): List<Place> {
        return PlaceFactory.makePlacesList()
    }
}
