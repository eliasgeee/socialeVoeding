package com.socialevoeding.usecases.categorieUseCases

import com.socialevoeding.domain.model.Category
import com.socialevoeding.domain.repositories.CategoryRepository
import com.socialevoeding.usecases.base.UseCase

class InitCategoriesUseCase(
    private val categoryRepository: CategoryRepository
) : UseCase<Unit>() {
    var categories: List<Category> = emptyList()
    override suspend fun executeOnBackground() {
        return categoryRepository.insertCategoriesIntoDatabase(categories)
    }
}