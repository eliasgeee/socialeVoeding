package com.socialevoeding.domain.model

sealed class Category(
    val id: Int,
    val name: String
) {
    class Food(
        id: Int,
        name: String = "FOOD"
    ) : Category(id, name)
}
