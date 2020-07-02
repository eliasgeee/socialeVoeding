package com.socialevoeding.presentation_android.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.socialevoeding.util_models.Either
import com.socialevoeding.domain.model.UserLocation
import com.socialevoeding.presentation_android.ViewItem
import com.socialevoeding.presentation_android.mappers.PlaceViewItemMapper
import com.socialevoeding.usecases.locationUseCases.GetLastKnownUserLocationUseCase
import com.socialevoeding.usecases.placeUseCases.GetPlacesUseCase

class PlacesViewModel(
    private val getPlacesUseCase: GetPlacesUseCase,
    private val getLastKnownUserLocationUseCase: GetLastKnownUserLocationUseCase
) : ViewModel() {

    private var _places = MutableLiveData<List<ViewItem.PlaceViewItem>>()
    val places: LiveData<List<ViewItem.PlaceViewItem>>
        get() = _places

    private var _goToPlace = MutableLiveData<ViewItem.PlaceViewItem>()
    val goToPlace: LiveData<ViewItem.PlaceViewItem>
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
            onComplete { places ->
                _places.postValue(places.data.map { place -> PlaceViewItemMapper.mapToViewItem(place) })
            }
        }
    }

    fun goToPlace(place: ViewItem.PlaceViewItem) {
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