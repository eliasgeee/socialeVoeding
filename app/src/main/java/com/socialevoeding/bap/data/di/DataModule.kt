package com.socialevoeding.bap.data.di

import com.socialevoeding.bap.data.dao.CategoryDao
import com.socialevoeding.bap.data.dao.PlaceDao
import com.socialevoeding.bap.data.database.LocalDb
import com.socialevoeding.bap.data.database.getDatabase
import com.socialevoeding.bap.data.repositories.CategoryRepositoryImpl
import com.socialevoeding.bap.data.repositories.LocationRepositoryImpl
import com.socialevoeding.bap.data.repositories.PlaceRepositoryImpl
import com.socialevoeding.bap.domain.repositories.CategoryRepository
import com.socialevoeding.bap.domain.repositories.LocationRepository
import com.socialevoeding.bap.domain.repositories.PlaceRepository
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

    single { PlaceRepositoryImpl(get(), get()) as PlaceRepository}
    single { CategoryRepositoryImpl(get()) as CategoryRepository}
    single { LocationRepositoryImpl(get()) as LocationRepository}
}

fun provideCategoryDao(db: LocalDb): CategoryDao {
    return db.categoryDao
}

fun provideLocationDao(db: LocalDb): PlaceDao {
    return db.placeDao
}