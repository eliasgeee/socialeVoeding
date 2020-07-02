package com.socialevoeding.presentation_android.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.socialevoeding.presentation_android.ViewItem

class PlaceDetailsViewModel : ViewModel() {

    private var _currentPlace = MutableLiveData<ViewItem.PlaceViewItem>()
    val currentPlace: LiveData<ViewItem.PlaceViewItem>
        get() = _currentPlace

    fun setCurrentPlace(place: ViewItem.PlaceViewItem) {
        _currentPlace.value = place
    }
}