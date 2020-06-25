package com.socialevoeding.presentation_android.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.socialevoeding.domain.model.Either
import com.socialevoeding.domain.model.UserLocation
import com.socialevoeding.presentation_android.mappers.PlaceViewItemMapper
import com.socialevoeding.presentation_android.viewItems.PlaceViewItem
import com.socialevoeding.usecases.locationUseCases.GetLastKnownUserLocationUseCase
import com.socialevoeding.usecases.placeUseCases.GetPlacesUseCase

class PlacesViewModel(
    private val getPlacesUseCase: GetPlacesUseCase,
    private val getLastKnownUserLocationUseCase: GetLastKnownUserLocationUseCase
) : ViewModel() {

    private var _places = MutableLiveData<List<PlaceViewItem>>()
    val places: LiveData<List<PlaceViewItem>>
        get() = _places

    private var _goToPlace = MutableLiveData<PlaceViewItem>()
    val goToPlace: LiveData<PlaceViewItem>
        get() = _goToPlace

    private var _currentLocation = MutableLiveData<String>()
    val currentPlaceLocation: LiveData<String>
    get() = _currentLocation

    init {
        getLastKnownUserLocationUseCase.execute {
            this.onComplete {
                when (it.data) {
                    is Either.Right -> _currentLocation.postValue((it.data as Either.Right<UserLocation>).b.cityName)
                }
            }
        }
    }

    fun loadPlaces() {
        getPlacesUseCase.execute {
            onComplete {
                _places.postValue(PlaceViewItemMapper.mapToViewItems(it.data))
            }
        }
    }

    fun goToPlace(place: PlaceViewItem) {
        _goToPlace.value = place
    }

    fun placeNavigated() {
        _goToPlace.value = null
    }

    override fun onCleared() {
        super.onCleared()
        getPlacesUseCase.unsubscribe()
    }

    fun setCurrentLocation(currentPlaceLocation: String) {
        _currentLocation.value = currentPlaceLocation
    }
}