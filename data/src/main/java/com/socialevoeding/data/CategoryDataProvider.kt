package com.socialevoeding.data

import com.socialevoeding.data_entities.CategoryEntity

object CategoryDataProvider {

fun getCategories(): List<CategoryEntity> {
    val cat = CategoryEntity(
        id = 1,
        name = "Food",
        type = "FOOD"
    )

    val mockCategories = ArrayList<CategoryEntity>()
    mockCategories.add(cat)
    return mockCategories
}
}