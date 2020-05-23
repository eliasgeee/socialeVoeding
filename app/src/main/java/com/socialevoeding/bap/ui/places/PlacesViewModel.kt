package com.socialevoeding.bap.ui.places

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.socialevoeding.bap.domain.model.Category
import com.socialevoeding.bap.domain.model.LocationModel
import com.socialevoeding.bap.domain.model.Place
import com.socialevoeding.bap.usecases.GetCurrentLocationFromGPSTrackerUseCase
import com.socialevoeding.bap.usecases.GetCurrentPlaceNameUseCase
import com.socialevoeding.bap.usecases.GetPlacesFromLocalDatabaseUseCase
import com.socialevoeding.bap.usecases.RefreshPlacesUseCase

class PlacesViewModel(
    private val getPlacesFromLocalDatabaseUseCase: GetPlacesFromLocalDatabaseUseCase,
    private val refreshPlacesUseCase: RefreshPlacesUseCase,
    private val getCurrentLocationFromGPSTrackerUseCase: GetCurrentLocationFromGPSTrackerUseCase,
    private val getCurrentPlaceNameUseCase: GetCurrentPlaceNameUseCase
) : ViewModel() {

    private var _places = MutableLiveData<List<Place>>()
    val places: LiveData<List<Place>>
        get() = _places

    private var _goToPlace = MutableLiveData<Place>()
    val goToPlace: LiveData<Place>
        get() = _goToPlace

    init {
        getCurrentLocationFromGPSTrackerUseCase.execute {
            onComplete { locationmodel ->
                getCurrentPlaceNameUseCase.currentLocationModel = locationmodel
                getCurrentPlaceNameUseCase.execute {
                    onComplete { locationWithCityName ->
                        refreshPlacesUseCase.currentLocationModel = LocationModel(
                            locationWithCityName.cityName,
                            locationWithCityName.latitude,
                            locationWithCityName.longitude)
                        refreshPlacesUseCase.currentCategorieName = "Food"
                        refreshPlacesUseCase.currentQueryNames = listOf("Foodbank", "Sociaalrestaurant", "Socialekruidenier")
                        refreshPlacesUseCase.execute {
                            onComplete {
                                getPlacesFromLocalDatabaseUseCase.execute {
                                    onComplete {
                                        _places.postValue(it)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        //TODO hardcoded
    }

    fun loadPlaces(){
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
        refreshPlacesUseCase.unsubscribe()
        getCurrentPlaceNameUseCase.unsubscribe()
        getCurrentLocationFromGPSTrackerUseCase.unsubscribe()
    }
}