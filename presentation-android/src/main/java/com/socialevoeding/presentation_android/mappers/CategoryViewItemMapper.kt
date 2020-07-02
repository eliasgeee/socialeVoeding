package com.socialevoeding.presentation_android.mappers

import com.socialevoeding.domain.model.Category
import com.socialevoeding.presentation_android.ViewItem
import com.socialevoeding.presentation_android.mappers.base.ViewItemMapper

object CategoryViewItemMapper : ViewItemMapper<ViewItem.CategoryViewItem, Category> {
    override fun mapToViewItem(model: Category): ViewItem.CategoryViewItem {
        return ViewItem.CategoryViewItem(
            id = model.id,
            name = model.name
        )
    }
}