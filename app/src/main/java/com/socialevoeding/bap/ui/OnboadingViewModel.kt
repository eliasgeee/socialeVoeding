package com.socialevoeding.bap.ui

import androidx.lifecycle.ViewModel
import com.socialevoeding.bap.usecases.RefreshPlacesUseCase

class OnboadingViewModel(
    private val refreshPlacesUseCase: RefreshPlacesUseCase
) : ViewModel(){

    fun getPlaces(){
        refreshPlacesUseCase.execute {  }
    }

    override fun onCleared() {
        super.onCleared()
        refreshPlacesUseCase.unsubscribe()
    }
}