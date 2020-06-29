package com.socialevoeding.usecases.categorieUseCases

import com.socialevoeding.domain.model.Category
import com.socialevoeding.domain.repositories.CategoryRepository
import com.socialevoeding.usecases.base.UseCase

class GetCategoriesUseCase(
    private val categoryRepository: CategoryRepository
) : UseCase<MutableList<Category>>(
    ioDispatcher : IoDispatcher
) {
    override suspend fun executeOnBackground(): MutableList<Category> {
        return categoryRepository.getCategories()
    }
}