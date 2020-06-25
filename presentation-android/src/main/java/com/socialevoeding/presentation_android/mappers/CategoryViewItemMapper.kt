package com.socialevoeding.presentation_android.mappers

import com.socialevoeding.domain.model.Category
import com.socialevoeding.presentation_android.mappers.base.ViewItemMapper
import com.socialevoeding.presentation_android.viewItems.CategoryViewItem

object CategoryViewItemMapper : ViewItemMapper<CategoryViewItem, Category> {
    override fun mapToViewItem(model: Category): CategoryViewItem {
        TODO("Not yet implemented")
    }

    override fun mapToViewItems(models: List<Category>): List<CategoryViewItem> {
        TODO("Not yet implemented")
    }
}