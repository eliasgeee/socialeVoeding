package com.socialevoeding.bap.domain.model

import java.io.Serializable

class Place(
    val id: Int,
    val name: String,
    val distance: Int,
    val telephoneNumber: String,
    val address: String,
    val webUrl: String,
    val isOpen: Boolean,
    val categoryId: Int,
    val img : String,
    val latitude : Double,
    val longitude : Double,
    val city : String
) : Serializable