package com.socialevoeding.usecases.di

import com.socialevoeding.usecases.*
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetPlacesFromLocalDatabaseUseCase(get()) }
    factory { InsertCategoriesIntoLocalDatabaseUseCase(get()) }
    factory { GetCategoriesFromLocalDatabaseUseCase(get()) }
    factory { InsertPlacesIntoLocalDatabaseUseCase(get()) }
    factory { RefreshPlacesUseCase(get()) }
    factory { GetCurrentPlaceNameUseCase(get()) }

}