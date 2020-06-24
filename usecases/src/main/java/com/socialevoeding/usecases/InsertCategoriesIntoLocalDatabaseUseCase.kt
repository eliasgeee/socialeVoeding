package com.socialevoeding.usecases

import com.socialevoeding.domain.model.Category
import com.socialevoeding.domain.repositories.CategoryRepository
import com.socialevoeding.usecases.base.UseCase

class InsertCategoriesIntoLocalDatabaseUseCase(
    private val categoryRepository: CategoryRepository
) : UseCase<Unit>() {
    var categories: List<Category> = emptyList()
    override suspend fun executeOnBackground() {
        return categoryRepository.insertCategoriesIntoDatabase(categories)
    }
}