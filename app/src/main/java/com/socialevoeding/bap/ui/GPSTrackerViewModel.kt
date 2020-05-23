package com.socialevoeding.bap.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.socialevoeding.bap.domain.model.Place
import com.socialevoeding.bap.usecases.RefreshPlacesUseCase
import com.socialevoeding.bap.usecases.StartGPSTrackerUseCase
import com.socialevoeding.bap.usecases.StopGPSTrackerUseCase

class GPSTrackerViewModel(
    private val startGPSTrackerUseCase: StartGPSTrackerUseCase,
    private val stopGPSTrackerUseCase: StopGPSTrackerUseCase,
    private val refreshPlacesUseCase: RefreshPlacesUseCase
) : ViewModel(){

    private var _currentPlace = MutableLiveData<String>()
    val currentPlace: LiveData<String>
        get() = _currentPlace

    fun startGpsTrackerAndLoadPlaces(){
        startGPSTrackerUseCase.execute {
            onComplete {
                _currentPlace.postValue(it)
                refreshPlacesUseCase.currentLocationName = "Ghent"
                refreshPlacesUseCase.currentCategorieName = "Food"
                refreshPlacesUseCase.currentQueryNames = listOf("Foodbank", "Sociaal restaurant")
                refreshPlacesUseCase.execute {
                }
            }
        }
    }

    fun startGpsTracker(){

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