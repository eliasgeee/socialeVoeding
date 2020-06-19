package com.socialevoeding.bap.ui.places

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.socialevoeding.bap.gps.GPSTracker
import com.socialevoeding.domain.model.LocationModel
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

    private var _currentLocation = MutableLiveData<LocationModel>()
    val currentLocation : LiveData<LocationModel>
    get() = _currentLocation

    init {
            }

    fun refreshPlaces(){
        refreshPlacesUseCase.currentLocationModel = LocationModel(
            cityName = "Gent",
            latitude = 51.054017,
            longitude = 3.7077823
        )
        refreshPlacesUseCase.execute {
            onComplete {

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

    fun loadPlaces(){
        val place = Place(
            1, "Poverello VZW", 7, "09 233 383 1",
            "Komijnstraat 5, 9000 Gent", "http://www.poverello.be", false, 1,
            "https://d3n8a8pro7vhmx.cloudfront.net/groengent/pages/3558/meta_images/original/Voedselbank_Winkelrekken_Medium_AdobeStock_206669622.jpeg?1569393926",
            10.0, 10.0, "Gent"
        )
        val place2 = Place(
            2, "Samenlevingsopbouw Gent VZW", 8, "09 223 95 15",
            " Blaisantvest 70, 9000 Gent", "http://www.poverello.be", false, 1,
            "https://cms.kaagent.be/uploads/album_pictures/image/44235-20190306201343.JPG",
            10.0, 10.0, "Gent"
        )
        val place3 = Place(
            2, "De Tinten VZW", 10, "09 244 57 42",
            " François Benardstraat 86, 9000 Gent", "http://www.poverello.be", false, 1,
            "https://www.gastvrijegemeente.be/sites/default/files/img_1941.jpg",
            10.0, 10.0, "Gent"
        )

        val hulp = ArrayList<Place>()
        hulp.add(place)
        hulp.add(place2)
        hulp.add(place3)

        _places.value = hulp

        getPlacesFromLocalDatabaseUseCase.execute {
            onComplete {
             //   _places.postValue(it)
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
    }

    fun setCurrentLocation(currentLocation: LocationModel) {
        _currentLocation.value = currentLocation
    }
}