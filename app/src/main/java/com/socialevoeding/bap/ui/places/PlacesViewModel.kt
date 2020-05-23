package com.socialevoeding.bap.ui.places

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.socialevoeding.bap.domain.model.Place
import com.socialevoeding.bap.usecases.GetPlacesFromLocalDatabaseUseCase

class PlacesViewModel(
    private val getPlacesFromLocalDatabaseUseCase: GetPlacesFromLocalDatabaseUseCase
) : ViewModel() {

    private var _places = MutableLiveData<List<Place>>()
    val places: LiveData<List<Place>>
        get() = _places

    private var _goToPlace = MutableLiveData<Place>()
    val goToPlace: LiveData<Place>
        get() = _goToPlace

    init {
                getPlacesFromLocalDatabaseUseCase.execute {
                    onComplete {
                        _places.postValue(it)
                    }
                }
    }

    fun goToPlace(place: Place) {
        _goToPlace.value = place
    }

    fun placeNavigated() {
        _goToPlace.value = null
    }

    override fun onCleared() {
        super.onCleared()
        getPlacesFromLocalDatabaseUseCase.unsubscribe()
    }
}