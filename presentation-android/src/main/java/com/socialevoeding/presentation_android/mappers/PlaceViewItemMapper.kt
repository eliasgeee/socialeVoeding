package com.socialevoeding.presentation_android.mappers

import com.socialevoeding.domain.model.place.Place
import com.socialevoeding.presentation_android.ViewItem
import com.socialevoeding.presentation_android.mappers.base.ViewItemMapper

object PlaceViewItemMapper : ViewItemMapper<ViewItem.PlaceViewItem, Place> {
    override fun mapToViewItem(model: Place): ViewItem.PlaceViewItem {
        return ViewItem.PlaceViewItem(
            name = model.name,
            telephoneNumber = model.telephoneNumber,
            webUrl = model.webUrl,
            img = model.img,
            address = model.address,
            cityName = model.cityName,
            latitude = model.latitude,
            longitude = model.longitude,
            openingHours = model.openingHours
        )
    }
}