package com.socialevoeding.bap.restful.dataTransferObjects

import com.socialevoeding.bap.data.entities.PlaceEntity
import com.socialevoeding.bap.data.mappers.DistanceMapper
import com.socialevoeding.bap.domain.model.LocationModel

data class PlaceDTO(
    val local_map: LocalMap? = null,
    val local_results: List<LocalResult>? = null,
    val organic_results: List<OrganicResult>? = null,
    val pagination: Pagination? = null,
    val request: Request? = null,
    val search_information: SearchInformation? = null,
    val search_parameters: SearchParameters? = null
)

fun PlaceDTO.asDatabaseModel(results : List<LocalResult>, categoryId : Int, currentLocation : LocationModel) : List<PlaceEntity> {
    return results.map {
        PlaceEntity(
            name = it.title,
            img = it.image_url,
            webUrl = it.url,
            address = it.address,
            latitude = it.coordinates.latitude,
            longitude = it.coordinates.longitude,
            categoryId = categoryId,
            city = currentLocation.cityName,
            distance = DistanceMapper.getDistanceBetweenTwoLocationsInMeters(
                lat1 = currentLocation.latitude,
                lon1 = currentLocation.longitude,
                lat2 = it.coordinates.latitude,
                lon2 = it.coordinates.longitude
            ).toInt()
        )
    }
}

data class Coordinates(
    val latitude: Double? = 0.0,
    val longitude: Double? = 0.0
)

data class LocalResult(
    val address: String = "",
    val coordinates: CoordinatesX = CoordinatesX(0.0, 0.0),
    val image_url: String = "",
    val position: Int = 0,
    val rating: Double = 0.0,
    val reviews: Int = 0,
    val title: String = "",
    val type: String = "",
    val url: String = ""
)

data class CoordinatesX(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)