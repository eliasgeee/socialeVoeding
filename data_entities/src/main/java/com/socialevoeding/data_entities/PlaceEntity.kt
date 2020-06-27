package com.socialevoeding.data_entities

data class PlaceEntity(
    val name: String = "",
    val distance: Int = 0,
    val telephoneNumber: String = "",
    val address: String = "",
    val webUrl: String = "",
    val isOpen: Boolean = false,
    val img: String,
    val latitude: Double,
    val longitude: Double,
    val city: String,
    val categoryId: Int = 0
)