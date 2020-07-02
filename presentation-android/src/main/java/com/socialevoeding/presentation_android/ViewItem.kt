package com.socialevoeding.presentation_android

import com.socialevoeding.domain.model.OpeningDay

//here we should put the text-to-speech logic so the viewmodel can iterate over ViewItems and call their text to speech method
//can be split into OverViewViewItems (sealed class containing PlaceOverViewViewItems,...)
// and DetailViewItems (sealed class containing PlaceDetailItems)
// because the screens when loading wifi items can possibly differ from food/shelter places

sealed class ViewItem {
    class CategoryViewItem(
        val id: Int,
        val name: String
    ) : ViewItem() {
        val img: Any? = null
    }
    class PlaceViewItem(
        val name: String? = "",
        val telephoneNumber: String? = "",
        val webUrl: String? = "",
        val img: String? = "",
        val address: String? = "",
        var cityName: String? = "",
        val latitude: Double? = Double.NaN,
        val longitude: Double? = Double.NaN,
        var openingHours: Array<OpeningDay>? = emptyArray(),
        val distance: Int? = 0,
        val isOpen: Boolean? = null
    ): ViewItem() {
    }
}