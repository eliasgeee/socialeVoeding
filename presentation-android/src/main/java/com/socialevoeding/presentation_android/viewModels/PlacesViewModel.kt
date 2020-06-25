package com.socialevoeding.presentation_android.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.socialevoeding.domain.model.PlaceLocation
import com.socialevoeding.domain.model.Place
import com.socialevoeding.usecases.locationUseCases.GetCurrentGeoLocationUseCase
import com.socialevoeding.usecases.placeUseCases.GetPlacesUseCase
import com.socialevoeding.usecases.placeUseCases.RefreshPlacesUseCase

class PlacesViewModel(
    private val getPlacesUseCase: GetPlacesUseCase,
    private val refreshPlacesUseCase: RefreshPlacesUseCase,
    private val getCurrentGeoLocationUseCase: GetCurrentGeoLocationUseCase
) : ViewModel() {

    private var _places = MutableLiveData<List<Place>>()
    val places: LiveData<List<Place>>
        get() = _places

    private var _goToPlace = MutableLiveData<Place>()
    val goToPlace: LiveData<Place>
        get() = _goToPlace

    private var _currentLocation = MutableLiveData<PlaceLocation>()
    val currentPlaceLocation: LiveData<PlaceLocation>
    get() = _currentLocation

    init {
            }

    fun refreshPlaces() {
        refreshPlacesUseCase.currentPlaceLocation = PlaceLocation(
            cityName = "Gent",
            latitude = 51.054017,
            longitude = 3.7077823
        )
        refreshPlacesUseCase.execute {
            onComplete {
                getPlacesUseCase.execute {
                    onComplete {
                        _places.postValue(it.data)
                    }
                }
                }
            onError {
                print(it.throwable.message)
            }
            }
        /*  getCurrentPlaceNameUseCase.currentLocationModel = LocationModel(
            cityName = "",
            latitude = 51.054017,
            longitude = 3.7077823
        )
        getCurrentPlaceNameUseCase.execute {
            onComplete { locationWithCityName ->
                refreshPlacesUseCase.currentLocationModel =
                    LocationModel(
                        cityName = locationWithCityName.data.cityName,
                        latitude =  locationWithCityName.data.latitude,
                        longitude = locationWithCityName.data.longitude
                    )
                _currentLocation.value = locationWithCityName.data
                refreshPlacesUseCase.currentCategorieName = "category.name"
                refreshPlacesUseCase.currentQueryNames = emptyList()
                refreshPlacesUseCase.execute {
                    onComplete {
                        getPlacesFromLocalDatabaseUseCase.execute {
                            onComplete {
                                _places.postValue(it.data)
                            }
                        }
                    }
                }
            }
        }*/
    }

    fun goToPlace(place: Place) {
        _goToPlace.value = place
    }

    fun placeNavigated() {
        _goToPlace.value = null
    }

    override fun onCleared() {
        super.onCleared()
        getPlacesUseCase.unsubscribe()
        refreshPlacesUseCase.unsubscribe()
        getCurrentGeoLocationUseCase.unsubscribe()
    }

    fun setCurrentLocation(currentPlaceLocation: PlaceLocation) {
        _currentLocation.value = currentPlaceLocation
    }
}