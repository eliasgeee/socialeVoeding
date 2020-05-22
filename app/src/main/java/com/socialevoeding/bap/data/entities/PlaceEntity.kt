package com.socialevoeding.bap.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.socialevoeding.bap.domain.model.Place

@Entity
class PlaceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val distance: Int = 0,
    val telephoneNumber: String = "",
    val address: String = "",
    val webUrl: String = "",
    val isOpen: Boolean = false,
    val categoryId: Int = 0,
    val venueId : String = ""
)

fun asDomainModel(placeEntity: PlaceEntity): Place {
    return Place(
        id = placeEntity.id,
        name = placeEntity.name,
        distance = placeEntity.distance,
        telephoneNumber = placeEntity.telephoneNumber,
        address = placeEntity.address,
        webUrl = placeEntity.webUrl,
        isOpen = placeEntity.isOpen,
        categoryId = placeEntity.categoryId
    )
}

fun List<PlaceEntity>.asDomainModel(): List<Place> {
    return map {
        asDomainModel(it)
    }
}

fun List<Place>.toEntity() : Array<PlaceEntity>{
    return map {
        PlaceEntity(
            id = it.id,
            name = it.name,
            distance = it.distance,
            telephoneNumber = it.telephoneNumber,
            address = it.address,
            webUrl = it.webUrl,
            isOpen = it.isOpen,
            categoryId = it.categoryId
        )
    }.toTypedArray()
}