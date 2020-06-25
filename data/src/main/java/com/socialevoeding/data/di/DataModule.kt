package com.socialevoeding.data.di

import com.socialevoeding.data.repositories.CategoryRepositoryImpl
import com.socialevoeding.data.repositories.UserLocationRepositoryImpl
import com.socialevoeding.data.repositories.PlaceRepositoryImpl
import com.socialevoeding.domain.repositories.CategoryRepository
import com.socialevoeding.domain.repositories.UserLocationRepository
import com.socialevoeding.domain.repositories.PlaceRepository
import org.koin.dsl.module

val dataModule = module {

    single { PlaceRepositoryImpl(
        get(),
        get()
    ) as PlaceRepository }
    single { CategoryRepositoryImpl(get()) as CategoryRepository }
    single { UserLocationRepositoryImpl(get(), get()) as UserLocationRepository }
}