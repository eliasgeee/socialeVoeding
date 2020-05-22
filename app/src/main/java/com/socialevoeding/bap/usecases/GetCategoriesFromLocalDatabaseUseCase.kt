package com.socialevoeding.bap.usecases

import com.socialevoeding.bap.domain.model.Category
import com.socialevoeding.bap.domain.repositories.CategoryRepository
import com.socialevoeding.bap.usecases.base.ErrorMapper
import com.socialevoeding.bap.usecases.base.UseCase

class GetCategoriesFromLocalDatabaseUseCase (
    errorMapper: ErrorMapper,
    private val categoryRepository: CategoryRepository
) : UseCase<MutableList<Category>>(errorMapper) {
    override suspend fun executeOnBackground(): MutableList<Category> {
        return categoryRepository.getCategories()
    }
}