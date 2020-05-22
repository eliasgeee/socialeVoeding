package com.socialevoeding.bap.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.socialevoeding.bap.domain.model.Place
import com.socialevoeding.bap.usecases.StartGPSTrackerUseCase

class GPSTrackerViewModel(
    private val startGPSTrackerUseCase: StartGPSTrackerUseCase
) : ViewModel(){

    private var _currentPlace = MutableLiveData<String>()
    val currentPlace: LiveData<String>
        get() = _currentPlace

    fun startGpsTracker(){
        startGPSTrackerUseCase.execute {
            onComplete {
                _currentPlace.postValue(it)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        startGPSTrackerUseCase.unsubscribe()
    }
}