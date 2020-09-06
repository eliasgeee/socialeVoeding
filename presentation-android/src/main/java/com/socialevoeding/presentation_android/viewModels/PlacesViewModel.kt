package com.socialevoeding.presentation_android.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.socialevoeding.util_models.Either
import com.socialevoeding.domain.model.UserLocation
import com.socialevoeding.presentation_android.ViewItem
import com.socialevoeding.presentation_android.ViewState
import com.socialevoeding.presentation_android.mappers.PlaceViewItemMapper
import com.socialevoeding.usecases.locationUseCases.GetLastKnownUserLocationUseCase
import com.socialevoeding.usecases.placeUseCases.GetPlacesUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class PlacesViewModel(
    private val getPlacesUseCase: GetPlacesUseCase,
    private val getLastKnownUserLocationUseCase: GetLastKnownUserLocationUseCase
) : ViewModel() {

    val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _goToPlace = MutableLiveData<ViewItem.PlaceViewItem>()
    val goToPlace: LiveData<ViewItem.PlaceViewItem>
        get() = _goToPlace

    private var _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState>
        get() = _viewState

    private var _currentLocationState = MutableLiveData<ViewState>()
    val currentLocationState: LiveData<ViewState>
    get() = _currentLocationState

    init { getLocation() }

    private fun getLocation() {
        getLastKnownUserLocationUseCase.execute {
            onComplete {
                when (it.data) {
                    is Either.Right -> {
                        _currentLocationState.postValue(ViewState.Succes((it.data as Either.Right<UserLocation>).b.cityName))
                    }
                }
                loadPlaces()
            }
            onError { _currentLocationState.value = ViewState.Error(it.throwable.message!!) }
        }
    }

    private fun loadPlaces() {
            getPlacesUseCase.execute {
                _viewState.value = ViewState.Loading()
                onComplete { places ->
                    viewModelScope.launch {
                        places.data.map { flow -> flow.map { place -> PlaceViewItemMapper.mapToViewItem(place)  } }.collect {
                            _viewState.value = ViewState.Succes(it)
                        }}
                    }
                    //_viewState = ViewState.Succes(places.data)
                    //ViewState.Succes(
                    //places.data.map { place -> PlaceViewItemMapper.mapToViewItem(place) })
                onError {
                    _viewState.value = ViewState.Error(it.throwable.message!!)
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
}