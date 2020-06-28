package com.socialevoeding.util_factories

import com.socialevoeding.domain.model.Category
import com.socialevoeding.util_datafactory.DataFactory

object CategoryFactory {

    fun makeCategory(): Category {
        return Category.Food(
            id = DataFactory.randomInt()
        )
    }

    fun makeCategoriesList(count: Int = 5): List<Category> {
        val categories = mutableListOf<Category>()
        var counter = count
        do {
            categories.add(makeCategory())
            counter--
        } while (counter != 0)
        return categories
    }
}