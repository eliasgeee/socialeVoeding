package com.socialevoeding.bap.usecases

import com.socialevoeding.bap.domain.model.Category
import com.socialevoeding.bap.domain.repositories.CategoryRepository
import com.socialevoeding.bap.usecases.base.ErrorMapper
import com.socialevoeding.bap.usecases.base.UseCase

class InsertCategoriesIntoLocalDatabaseUseCase (
    errorMapper: ErrorMapper,
    private val categoryRepository: CategoryRepository
) : UseCase<Boolean>(errorMapper) {
    var categories = ArrayList<Category>()
    override suspend fun executeOnBackground(): Boolean {
        return categoryRepository.insertCategoriesIntoDatabase(categories)
    }
}