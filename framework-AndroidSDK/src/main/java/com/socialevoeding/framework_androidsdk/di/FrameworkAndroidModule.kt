package com.socialevoeding.framework_androidsdk.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.socialevoeding.data.datasources.local.database.CategoryLocalDataSource
import com.socialevoeding.data.datasources.remote.NetworkConfig
import com.socialevoeding.framework_androidsdk.device.gps.GPSTracker
import com.socialevoeding.framework_androidsdk.local.room.dao.CategoryDao
import com.socialevoeding.framework_androidsdk.local.room.dao.PlaceDao
import com.socialevoeding.framework_androidsdk.local.room.database.LocalDb
import com.socialevoeding.framework_androidsdk.local.room.database.getDatabase
import com.socialevoeding.framework_androidsdk.local.room.datasources.RoomCategoryDataSource
import com.socialevoeding.framework_androidsdk.local.room.datasources.RoomPlaceDataSource
import com.socialevoeding.framework_androidsdk.remote.retrofit.apiServices.GeoLocationApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val frameworkAndroidModule = module {

    single {
        getDatabase(
            androidContext()
        )
    }

    single { provideCategoryDao(get()) }
    single { provideLocationDao(get()) }

    single { RoomCategoryDataSource(get()) as CategoryLocalDataSource }
    single { RoomPlaceDataSource(get()) as RoomPlaceDataSource }

    single { provideRetrofit() }
    single { provideGeolocationApi() }

    single { GPSTracker() }
}

fun provideCategoryDao(db: LocalDb): CategoryDao {
    return db.categoryDao
}

fun provideLocationDao(db: LocalDb): PlaceDao {
    return db.placeDao
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(NetworkConfig.BASE_URL_GEOLOCATION)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
}

fun provideMoshi(): Moshi {
    return Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}

fun provideGeolocationApi(): GeoLocationApiService {
    return provideRetrofit()
        .create(GeoLocationApiService::class.java)
}