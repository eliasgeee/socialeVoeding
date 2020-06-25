package com.socialevoeding.domain.model

import java.io.Serializable

sealed class Category(
    val id: Int,
    val name: String
){
    class Food(
        id : Int,
        name: String = "FOOD"
    ) : Category(id, name)
}
