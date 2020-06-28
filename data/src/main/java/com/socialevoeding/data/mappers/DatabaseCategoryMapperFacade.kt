package com.socialevoeding.data.mappers

import com.socialevoeding.data.mappers.base.CategoryMapperFacade
import com.socialevoeding.data_entities.CategoryEntity
import com.socialevoeding.domain.model.Category
import com.socialevoeding.li.extensions.getNormalizedName

object DatabaseCategoryMapperFacade :
    CategoryMapperFacade<CategoryEntity, Category> {
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
        return CategoryEntity(
                id = model.id,
                name = model.name,
                type = model.name.getNormalizedName()
            )
    }

    override fun mapFromEntities(entities: List<CategoryEntity>): List<Category> {
        return entities.map { mapFromEntity(it) }
    }

    override fun mapToEntities(models: List<Category>): List<CategoryEntity> {
        return models.map { mapToEntity(it) }
    }
}