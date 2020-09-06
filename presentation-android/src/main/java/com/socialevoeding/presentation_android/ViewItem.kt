package com.socialevoeding.presentation_android

import android.os.Parcelable
import com.socialevoeding.domain.model.OpeningDay
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

// here we should put the text-to-speech logic so the viewmodel can iterate over ViewItems and call their text to speech method
// can be split into OverViewViewItems (sealed class containing PlaceOverViewViewItems,...)
// and DetailViewItems (sealed class containing PlaceDetailItems)
// because the screens when loading wifi items can possibly differ from food/shelter places

sealed class ViewItem {
    @Parcelize
    class CategoryViewItem(
        val id: Int,
        val name: String
    ) : ViewItem(), Parcelable {
        val img: Any? = null
    }
    @Parcelize
    class PlaceViewItem(
        val name: String? = "",
        val telephoneNumber: String? = "",
        val webUrl: String? = "",
        val img: String? = "",
        val address: String? = "",
        var cityName: String? = "",
        val latitude: Double? = Double.NaN,
        val longitude: Double? = Double.NaN,
        var openingHours: @RawValue Array<OpeningDay>? = emptyArray(),
        val distance: Int? = 0,
        val isOpen: Boolean? = null
    ) : ViewItem(), Parcelable
}