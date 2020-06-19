package com.socialevoeding.bap.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.socialevoeding.domain.model.Category
import com.socialevoeding.domain.model.Place
import com.squareup.moshi.Json
import com.squareup.moshi.Moshi

@Entity
class CategoryEntity(
    @PrimaryKey()
    val id: Int,
    val name: String
)

fun asDomainModel(categoryEntity: CategoryEntity): Category {
    return Category(
        id = categoryEntity.id,
        name = categoryEntity.name
    )
}

fun List<CategoryEntity>.asDomainModel(): List<Category> {
    return map {
        asDomainModel(it)
    }
}


fun List<Category>.toEntity() : List<CategoryEntity>{
    return map {
        CategoryEntity(
            id = it.id,
            name = it.name
        )
    }
}