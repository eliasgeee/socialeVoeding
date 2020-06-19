package com.socialevoeding.bap.restful.dataTransferObjects

import com.socialevoeding.bap.data.entities.PlaceEntity
import com.socialevoeding.bap.data.mappers.DistanceMapper
import com.socialevoeding.domain.model.LocationModel
import pl.droidsonroids.jspoon.annotation.Selector

class PlaceDTO(
    @Selector("section-layout section-scrollbox scrollable-y scrollable-show section-layout-flex-vertical")
    val places : List<LocalResult>
)

fun PlaceDTO.asDatabaseModel(results : List<LocalResult>, categoryId : Int, currentLocation : LocationModel) : List<PlaceEntity> {
    return results.map {
        PlaceEntity(
            name = it.title,
            img = it.image_url,
            webUrl = it.url,
            address = it.address,
            latitude = 0.0,
            longitude = 0.0,
            categoryId = categoryId,
            city = currentLocation.cityName,
            distance = DistanceMapper.getDistanceBetweenTwoLocationsInMeters(
                lat1 = currentLocation.latitude,
                lon1 = currentLocation.longitude,
                lat2 = 0.0,
                lon2 = 0.0
            ).toInt()
        )
    }
}

class Coordinates(
    val latitude: Double? = 0.0,
    val longitude: Double? = 0.0
)

class LocalResult(
    val address: String = "",
    val coordinates: Coordinates = Coordinates(0.0, 0.0),
    val image_url: String = "",
    val position: Int = 0,
    val rating: Double = 0.0,
    val reviews: Int = 0,
    @Selector(".section-result > .section-result-content > .section-result-text-content > .section-result-header-container > .section-result-header > .section-result-title-container > h3 > span")
    val title: String = "",
    val type: String = "",
    val url: String = ""
)
