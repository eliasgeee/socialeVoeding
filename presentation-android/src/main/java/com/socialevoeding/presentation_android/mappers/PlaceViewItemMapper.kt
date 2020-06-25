package com.socialevoeding.presentation_android.mappers

import com.socialevoeding.domain.model.Place
import com.socialevoeding.presentation_android.mappers.base.ViewItemMapper
import com.socialevoeding.presentation_android.viewItems.PlaceViewItem

object PlaceViewItemMapper : ViewItemMapper<PlaceViewItem, Place> {
    override fun mapToViewItem(model: Place): PlaceViewItem {
        TODO("Not yet implemented")
    }

    override fun mapToViewItems(models: List<Place>): List<PlaceViewItem> {
        TODO("Not yet implemented")
    }
}