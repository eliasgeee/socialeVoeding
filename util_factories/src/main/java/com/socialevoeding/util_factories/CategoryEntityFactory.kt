package com.socialevoeding.util_factories

import com.socialevoeding.data_entities.CategoryEntity
import com.socialevoeding.util_datafactory.DataFactory

object CategoryEntityFactory {
    fun makeCategoryEntity(): CategoryEntity {
        return CategoryEntity(
            id = DataFactory.randomInt(),
            type = DataFactory.randomString(),
            name = DataFactory.randomString()
        )
    }

    fun makeCategoryEntitiesList(count: Int = 5): List<CategoryEntity> {
        val categories = mutableListOf<CategoryEntity>()
        var counter = count
        do {
            categories.add(makeCategoryEntity())
            counter--
        } while (counter != 0)
        return categories
    }
}