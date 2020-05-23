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
}