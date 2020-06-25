package com.socialevoeding.data.mappers

import com.socialevoeding.data.dtos.local.database.CategoryEntity
import com.socialevoeding.data.mappers.base.DatabaseMapperFacade
import com.socialevoeding.domain.model.Category
import com.socialevoeding.li.extensions.getNormalizedName

object DatabaseCategoryMapperFacade :
    DatabaseMapperFacade<CategoryEntity, Category> {
    override fun mapFromEntity(entity: CategoryEntity): Category {
        var category: Category? = null
        if (entity.type == "FOOD")
            category = Category.Food(
                id = entity.id,
                name = entity.name
            )
        return category!!
    }

    override fun mapToEntity(model: Category): CategoryEntity {
        when (model) {
            is Category.Food -> return CategoryEntity(
                id = model.id,
                name = model.name,
                type = model.name.getNormalizedName()
            )
        }
    }

    override fun mapFromEntities(entities: List<CategoryEntity>): List<Category> {
        return entities.map { mapFromEntity(it) }
    }

    override fun mapToEntities(models: List<Category>): List<CategoryEntity> {
        return models.map { mapToEntity(it) }
    }
}