package com.socialevoeding.bap.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.socialevoeding.domain.model.PlaceLocation
import com.socialevoeding.framework.device.gps.GPSTracker

class GPSTrackerViewModel(
) : ViewModel(){

    private var _currentLocation = MutableLiveData<PlaceLocation>()
    val currentPlaceLocation: LiveData<PlaceLocation>
        get() = _currentLocation

    private var _gpsTracker = MutableLiveData<com.socialevoeding.framework.device.gps.GPSTracker>()
    val gpsTracker: LiveData<com.socialevoeding.framework.device.gps.GPSTracker>
        get() = _gpsTracker

    fun startGpsTrackerAndLoadPlaces(gpsTracker : com.socialevoeding.framework.device.gps.GPSTracker){
        _gpsTracker.value = gpsTracker
    }
}