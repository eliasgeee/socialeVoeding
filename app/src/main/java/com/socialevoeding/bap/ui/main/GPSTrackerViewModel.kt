package com.socialevoeding.bap.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.socialevoeding.bap.domain.model.LocationModel
import com.socialevoeding.bap.domain.model.Place
import com.socialevoeding.bap.usecases.GetCurrentLocationFromGPSTrackerUseCase
import com.socialevoeding.bap.usecases.RefreshPlacesUseCase
import com.socialevoeding.bap.usecases.StartGPSTrackerUseCase
import com.socialevoeding.bap.usecases.StopGPSTrackerUseCase

class GPSTrackerViewModel(
    private val startGPSTrackerUseCase: StartGPSTrackerUseCase,
    private val stopGPSTrackerUseCase: StopGPSTrackerUseCase
) : ViewModel(){

    private var _currentLocation = MutableLiveData<LocationModel>()
    val currentLocation: LiveData<LocationModel>
        get() = _currentLocation

    fun startGpsTrackerAndLoadPlaces(){
        startGPSTrackerUseCase.execute {
            onComplete {}
        }
    }

    fun startGpsTracker(){
        startGPSTrackerUseCase.execute {  }
    }

    fun stopGpsTracker(){
        stopGPSTrackerUseCase.execute {  }
    }

    override fun onCleared() {
        super.onCleared()
        startGPSTrackerUseCase.unsubscribe()
        stopGPSTrackerUseCase.unsubscribe()
    }
}