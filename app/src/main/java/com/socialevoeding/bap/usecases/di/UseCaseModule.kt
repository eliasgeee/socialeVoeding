package com.socialevoeding.bap.usecases.di

import com.socialevoeding.bap.usecases.*
import com.socialevoeding.bap.usecases.base.ErrorMapper
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetPlacesFromLocalDatabaseUseCase(get(), get()) }
    factory { RefreshPlacesUseCase(get(), get()) }
    factory { InsertCategoriesIntoLocalDatabaseUseCase(get(), get()) }
    factory { GetCategoriesFromLocalDatabaseUseCase(get(), get())}
    factory { StartGPSTrackerUseCase(get(), get()) }

    single { ErrorMapper() }
}