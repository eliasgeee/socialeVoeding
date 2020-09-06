package com.socialevoeding.usecases.categorieUseCases

import com.socialevoeding.domain.model.Category
import com.socialevoeding.domain.repositories.CategoryRepository
import com.socialevoeding.usecases.base.UseCase

class GetCategoriesUseCase(
    private val categoryRepository: CategoryRepository
) : UseCase<List<Category>>() {

    override suspend fun executeOnBackground(): List<Category> = categoryRepository.getCategories()
}