package com.socialevoeding.bap.usecases.di

import com.socialevoeding.bap.usecases.*
import com.socialevoeding.bap.usecases.base.ErrorMapper
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetPlacesFromLocalDatabaseUseCase(get(), get()) }
    factory { InsertCategoriesIntoLocalDatabaseUseCase(get(), get()) }
    factory { GetCategoriesFromLocalDatabaseUseCase(get(), get())}
    factory { InsertPlacesIntoLocalDatabaseUseCase(get(), get()) }
    factory { RefreshPlacesUseCase(get(), get()) }
    factory { StartGPSTrackerUseCase(get(), get()) }
    factory { StopGPSTrackerUseCase(get(), get()) }
    factory { GetCurrentLocationFromGPSTrackerUseCase(get(), get()) }
    factory { GetCurrentPlaceNameUseCase(get(), get())}

    single { ErrorMapper() }
}