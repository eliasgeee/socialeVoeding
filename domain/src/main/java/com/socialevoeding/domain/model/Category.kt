package com.socialevoeding.domain.model

sealed class Category(
    val id: Int,
    val name: String
) {
    class Food(
        id: Int,
        name: String = "FOOD"
    ) : Category(id, name)

    class Shelter(
        id: Int,
        name: String = "SHELTER"
    ) : Category(id, name)

    class WiFi(
        id: Int,
        name: String = "WIFI"
    ) : Category(id, name)
}
