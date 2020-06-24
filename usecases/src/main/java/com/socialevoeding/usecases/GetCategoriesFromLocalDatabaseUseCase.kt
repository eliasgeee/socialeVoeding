package com.socialevoeding.usecases

import com.socialevoeding.domain.model.Category
import com.socialevoeding.domain.repositories.CategoryRepository
import com.socialevoeding.usecases.base.UseCase

class GetCategoriesFromLocalDatabaseUseCase(
    private val categoryRepository: CategoryRepository
) : UseCase<MutableList<Category>>() {
    override suspend fun executeOnBackground(): MutableList<Category> {
        return categoryRepository.getCategories()
    }
}