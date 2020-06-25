package com.socialevoeding.presentation_android.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.socialevoeding.presentation_android.viewItems.PlaceViewItem

class PlaceDetailsViewModel : ViewModel() {

    private var _currentPlace = MutableLiveData<PlaceViewItem>()
    val currentPlace: LiveData<PlaceViewItem>
        get() = _currentPlace

    fun setCurrentLocation(place: PlaceViewItem) {
        _currentPlace.value = place
    }
}