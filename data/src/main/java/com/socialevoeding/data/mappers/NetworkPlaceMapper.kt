package com.socialevoeding.data.mappers

import com.socialevoeding.data.dtos.remote.NetworkPlace
import com.socialevoeding.data.mappers.base.NetworkMapper
import com.socialevoeding.domain.model.OpeningDay
import com.socialevoeding.domain.model.place.Place
import com.socialevoeding.domain.model.place.buildPlace
import com.socialevoeding.util_factories.PlaceFactory
import com.socialevoeding.util_models.Either

// TODO mock objects
object NetworkPlaceMapper :
    NetworkMapper<NetworkPlace, Place> {
    override fun mapToDomainObject(networkObject: NetworkPlace): Either<Unit, Place> {
        var openingDays = emptyArray<OpeningDay>()
        if(networkObject.openingHours.isNotEmpty()){
            openingDays = networkObject.openingHours.map { e -> OpeningDay(0, e.dayOfWeek, e.status ) }.toTypedArray()
        }
        return buildPlace {
            name = networkObject.name
            address = networkObject.address
            img = networkObject.img_url
            longitude = networkObject.networkCoordinates.longitude!!
            latitude = networkObject.networkCoordinates.latitude!!
            telephoneNumber = networkObject.telephoneNumber
            cityName = networkObject.cityName
            webUrl = networkObject.url
            openingHours = openingDays
        }
    }

    override fun mapToListOfDomainObjects(entities: List<NetworkPlace>): List<Place> {
        val places = ArrayList<Place>()
        for (entity in entities){
            val result = mapToDomainObject(entity)
            if(result is Either.Right) places.add(result.b)
        }
        return places
    }
}
