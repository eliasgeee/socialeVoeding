package com.socialevoeding.bap.ui.places

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.socialevoeding.domain.model.PlaceLocation
import com.socialevoeding.domain.model.Place
import com.socialevoeding.usecases.GetCurrentPlaceNameUseCase
import com.socialevoeding.usecases.GetPlacesFromLocalDatabaseUseCase
import com.socialevoeding.usecases.RefreshPlacesUseCase

class PlacesViewModel(
    private val getPlacesFromLocalDatabaseUseCase: GetPlacesFromLocalDatabaseUseCase,
    private val refreshPlacesUseCase: RefreshPlacesUseCase,
    private val getCurrentPlaceNameUseCase: GetCurrentPlaceNameUseCase
) : ViewModel() {

    private var _places = MutableLiveData<List<Place>>(emptyList())
    val places: LiveData<List<Place>>
        get() = _places

    private var _goToPlace = MutableLiveData<Place>()
    val goToPlace: LiveData<Place>
        get() = _goToPlace

    private var _currentLocation = MutableLiveData<PlaceLocation>()
    val currentPlaceLocation : LiveData<PlaceLocation>
    get() = _currentLocation

    init {
            }

    fun refreshPlaces(){
        refreshPlacesUseCase.currentPlaceLocation = PlaceLocation(
            cityName = "Gent",
            latitude = 51.054017,
            longitude = 3.7077823
        )
        refreshPlacesUseCase.execute {
            onComplete {
                getPlacesFromLocalDatabaseUseCase.execute {
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
        getPlacesFromLocalDatabaseUseCase.unsubscribe()
        refreshPlacesUseCase.unsubscribe()
        getCurrentPlaceNameUseCase.unsubscribe()
    }

    fun setCurrentLocation(currentPlaceLocation: PlaceLocation) {
        _currentLocation.value = currentPlaceLocation
    }
}