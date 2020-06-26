package com.socialevoeding.li.factory

import com.socialevoeding.domain.model.Category

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