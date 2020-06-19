package com.socialevoeding.bap.data.di

import com.socialevoeding.bap.data.dao.CategoryDao
import com.socialevoeding.bap.data.dao.PlaceDao
import com.socialevoeding.bap.data.database.LocalDb
import com.socialevoeding.bap.data.database.getDatabase
import com.socialevoeding.bap.data.repositories.CategoryRepositoryImpl
import com.socialevoeding.bap.data.repositories.GeoLocationRepositoryImpl
import com.socialevoeding.bap.data.repositories.PlaceRepositoryImpl
import com.socialevoeding.domain.repositories.CategoryRepository
import com.socialevoeding.domain.repositories.GeoLocationRepository
import com.socialevoeding.domain.repositories.PlaceRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single {
        getDatabase(
            androidContext()
        )
    }

    single { provideCategoryDao(get()) }
    single { provideLocationDao(get()) }

    single { PlaceRepositoryImpl(get(), get()) as PlaceRepository }
    single { CategoryRepositoryImpl(get()) as CategoryRepository }
    single { GeoLocationRepositoryImpl(get()) as GeoLocationRepository }
}

fun provideCategoryDao(db: LocalDb): CategoryDao {
    return db.categoryDao
}

fun provideLocationDao(db: LocalDb): PlaceDao {
    return db.placeDao
}