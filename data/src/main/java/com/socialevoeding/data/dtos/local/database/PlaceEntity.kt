package com.socialevoeding.data.dtos.local.database

data class PlaceEntity (
    val id: Int = 0,
    val name: String = "",
    val distance: Int = 0,
    val telephoneNumber: String = "",
    val address: String = "",
    val webUrl: String = "",
    val isOpen: Boolean = false,
    val img : String,
    val latitude : Double,
    val longitude : Double,
    val city : String,
    val categoryId: Int = 0
)