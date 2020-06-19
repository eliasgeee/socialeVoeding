package com.socialevoeding.bap.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.socialevoeding.domain.model.LocationModel
import com.socialevoeding.bap.gps.GPSTracker

class GPSTrackerViewModel(
) : ViewModel(){

    private var _currentLocation = MutableLiveData<LocationModel>()
    val currentLocation: LiveData<LocationModel>
        get() = _currentLocation

    private var _gpsTracker = MutableLiveData<GPSTracker>()
    val gpsTracker: LiveData<GPSTracker>
        get() = _gpsTracker

    fun startGpsTrackerAndLoadPlaces(gpsTracker : GPSTracker){
        _gpsTracker.value = gpsTracker
    }
}