package com.socialevoeding.presentation_android.viewModels

import androidx.lifecycle.ViewModel
import com.socialevoeding.domain.model.Coordinates
import com.socialevoeding.util_models.Either
import com.socialevoeding.domain.model.UserLocation
import com.socialevoeding.li.extensions.getNormalizedName
import com.socialevoeding.usecases.categorieUseCases.GetCategoriesUseCase
import com.socialevoeding.usecases.categorieUseCases.InitCategoriesUseCase
import com.socialevoeding.usecases.locationUseCases.GetCurrentCoordinatesUseCase
import com.socialevoeding.usecases.locationUseCases.GetCurrentGeoLocationUseCase
import com.socialevoeding.usecases.locationUseCases.GetLastKnownUserLocationUseCase
import com.socialevoeding.usecases.placeUseCases.RefreshPlacesUseCase

class InitViewModel(
    private val getCurrentGeoLocationUseCase: GetCurrentGeoLocationUseCase,
    private val getLastKnownUserLocationUseCase: GetLastKnownUserLocationUseCase,
    private val getCurrentCoordinatesUseCase: GetCurrentCoordinatesUseCase,
    private val initCategoriesUseCase: InitCategoriesUseCase,
    private val refreshPlacesUseCase: RefreshPlacesUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    fun updateLocationAndPlaces() {
      //  getCurrentCoordinatesUseCase.execute {
     //       onComplete {
     //           getCurrentGeoLocationUseCase.currentCoordinates = it.data
                getCurrentGeoLocationUseCase.currentCoordinates = Coordinates(latitude = 51.0543, longitude = 3.7174)
                getCurrentGeoLocationUseCase.execute {
                    onComplete { currentLocation ->
                        getLastKnownUserLocationUseCase.execute {
                            onComplete { lastKnownLocation ->
                                when (lastKnownLocation.data) {
                                    // No last known location in cache -> initialize data
                                    is Either.Left -> initData(currentLocation.data)
                                    is Either.Right -> checkIfRefreshIsNecessary(
                                        currentLocation = currentLocation.data,
                                        lastKnownLocation = (lastKnownLocation.data as Either.Right<UserLocation>).b
                                    )
                                }
                            }
                        }
                    }
                }
            }
    //    }
  //  }

    private fun checkIfRefreshIsNecessary(currentLocation: UserLocation, lastKnownLocation: UserLocation) {
        if (currentLocation.cityName.getNormalizedName() != lastKnownLocation.cityName.getNormalizedName())
            refreshPlaces(currentLocation)
    }

    private fun initData(currentLocation: UserLocation) {
        initCategoriesUseCase.execute {
            onComplete {
                refreshPlaces(currentLocation)
            }
        }
    }

    private fun refreshPlaces(currentLocation: UserLocation) {
        getCategoriesUseCase.execute {
            onComplete {
                it.data.forEach {
                    refreshPlacesUseCase.currentCategorieName = it.name
                    refreshPlacesUseCase.userLocation = currentLocation
                    refreshPlacesUseCase.execute {
                    }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        getCurrentCoordinatesUseCase.unsubscribe()
        getCurrentGeoLocationUseCase.unsubscribe()
        getLastKnownUserLocationUseCase.unsubscribe()
        initCategoriesUseCase.unsubscribe()
        refreshPlacesUseCase.unsubscribe()
        getCategoriesUseCase.unsubscribe()
    }
}