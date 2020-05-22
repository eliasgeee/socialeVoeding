package com.socialevoeding.bap.restful.dataTransferObjects

import com.socialevoeding.bap.data.entities.PlaceEntity

data class PlaceDTO(
    val meta: Meta?,
    val response: Response?
)

fun PlaceDTO.asDatabaseModel(venues : List<Venue>) : Array<PlaceEntity> {
    return venues.map {
        PlaceEntity(
            venueId = it.id!!,
            name = it.name!!,
            distance = it.location!!.distance!!,
            address = it.location.address!!
        )
    }.toTypedArray()
}