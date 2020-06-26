package com.socialevoeding.usecases.di

import com.socialevoeding.usecases.categorieUseCases.GetCategoriesUseCase
import com.socialevoeding.usecases.categorieUseCases.InitCategoriesUseCase
import com.socialevoeding.usecases.locationUseCases.GetCurrentCoordinatesUseCase
import com.socialevoeding.usecases.locationUseCases.GetCurrentGeoLocationUseCase
import com.socialevoeding.usecases.locationUseCases.GetLastKnownUserLocationUseCase
import com.socialevoeding.usecases.placeUseCases.GetPlacesUseCase
import com.socialevoeding.usecases.placeUseCases.RefreshPlacesUseCase
import com.socialevoeding.usecases.placeUseCases.SavePlacesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetPlacesUseCase(get()) }
    factory {
        InitCategoriesUseCase(
            get()
        )
    }
    factory {
        GetCategoriesUseCase(
            get()
        )
    }
    factory { SavePlacesUseCase(get()) }
    factory { RefreshPlacesUseCase(get()) }
    factory {
        GetCurrentGeoLocationUseCase(
            get()
        )
    }
    factory { GetLastKnownUserLocationUseCase(get()) }
    factory { GetCurrentCoordinatesUseCase(get()) }
}