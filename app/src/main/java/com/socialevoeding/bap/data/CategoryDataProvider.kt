package com.socialevoeding.bap.data

import com.socialevoeding.bap.data.entities.CategoryEntity
import com.socialevoeding.bap.data.entities.PlaceEntity

object CategoryDataProvider {

fun getCategories () : List<CategoryEntity> {
    val cat = CategoryEntity(1, "Food")
    val cat3 = CategoryEntity(2, "Category")
    val cat4 = CategoryEntity(3, "Category")
    val cat2 = CategoryEntity(4, "Category")
    val cat5 = CategoryEntity(5, "Category")
    val cat6 = CategoryEntity(6, "Category")
    val mockCategories = ArrayList<CategoryEntity>()
    mockCategories.add(cat)
    mockCategories.add(cat2)
    mockCategories.add(cat3)
    mockCategories.add(cat4)
    mockCategories.add(cat5)
    mockCategories.add(cat6)
    return mockCategories
}

    fun getPlaces() : List<PlaceEntity>{
        val place1 = PlaceEntity(1, "Sociaal resto", 3, "0987654321", "Voedselstraat 3, Gent", "www.sociaal.be", true, 1)
        val place2 = PlaceEntity(2, "Sociale kruidenier", 20, "0987654321", "Brugse Poort 3, Gent", "www.sociaal.be", true, 1)
        val place3 = PlaceEntity(3, "Voedselbank", 2, "0987654321", "Burgstraat 3, Gent", "www.sociaal.be", false, 1)
        val place4 = PlaceEntity(4, "Nog een voedselbank", 30, "0987654321", "Coronavaart 3, Gent", "www.sociaal.be", true, 1)
        val place5 = PlaceEntity(5, "Eteeuh", 30, "0987654321", "Rue du bourgoisie 3, Gent", "www.sociaal.be", false, 1)
        val mockPlaces = ArrayList<PlaceEntity>()
        mockPlaces.add(place1)
        mockPlaces.add(place2)
        mockPlaces.add(place4)
        mockPlaces.add(place3)
        mockPlaces.add(place5)
        return mockPlaces
    }
}