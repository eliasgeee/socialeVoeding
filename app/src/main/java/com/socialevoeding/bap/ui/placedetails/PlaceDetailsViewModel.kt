package com.socialevoeding.bap.ui.placedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.socialevoeding.domain.model.Place

class PlaceDetailsViewModel : ViewModel() {

    private var _currentPlace = MutableLiveData<Place>()
    val currentPlace: LiveData<Place>
        get() = _currentPlace

    fun setCurrentLocation(place: Place) {
        _currentPlace.value = place
    }
}