package com.socialevoeding.data.mappers

import com.socialevoeding.data.dtos.local.database.CategoryEntity
import com.socialevoeding.data.mappers.base.DatabaseMapperFacade
import com.socialevoeding.domain.model.Category

object DatabaseCategoryMapperFacade :
    DatabaseMapperFacade<CategoryEntity, Category> {
    override fun mapFromEntity(entity: CategoryEntity): Category {
        TODO("Not yet implemented")
    }

    override fun mapToEntity(model: Category): CategoryEntity {
        TODO("Not yet implemented")
    }

    override fun mapFromEntities(entities: List<CategoryEntity>): List<Category> {
        TODO("Not yet implemented")
    }

    override fun mapToEntities(models: List<Category>): List<CategoryEntity> {
        TODO("Not yet implemented")
    }
}