package com.socialevoeding.framework_androidsdk.di

import android.content.Context
import android.content.SharedPreferences
import android.location.LocationManager
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.socialevoeding.data.datasources.device.CurrentLocationDataSource
import com.socialevoeding.data.datasources.local.cache.UserLocationCacheDataSource
import com.socialevoeding.data.datasources.local.database.CategoryLocalDataSource
import com.socialevoeding.data.datasources.remote.NetworkConfig
import com.socialevoeding.data.dtos.local.device.UserLocationDTO
import com.socialevoeding.framework_androidsdk.device.gps.LocationGPSDataSource
import com.socialevoeding.framework_androidsdk.local.room.dao.CategoryDao
import com.socialevoeding.framework_androidsdk.local.room.dao.PlaceDao
import com.socialevoeding.framework_androidsdk.local.room.database.LocalDb
import com.socialevoeding.framework_androidsdk.local.room.database.getDatabase
import com.socialevoeding.framework_androidsdk.local.room.datasources.RoomCategoryDataSource
import com.socialevoeding.framework_androidsdk.local.room.datasources.RoomPlaceDataSource
import com.socialevoeding.framework_androidsdk.local.sharedPreferences.SharedPrefUserLocationDataSource
import com.socialevoeding.framework_androidsdk.remote.retrofit.apiServices.GeoLocationApiService
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val STORAGE = "com.socialevoeding.bap.USERLOCATION"

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
    single { SharedPrefUserLocationDataSource(get(), get()) as UserLocationCacheDataSource }
    single { LocationGPSDataSource(get()) as CurrentLocationDataSource }

    single { provideRetrofit() }
    single { provideGeolocationApi() }
    single { provideMoshiAdapter() }

    single { provideLocationManager(androidContext()) }

    single {
        getSharedPrefs(androidContext())
    }
}

fun provideLocationManager(context: Context): LocationManager {
    return context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
}

fun getSharedPrefs(context: Context): SharedPreferences {
    return context.getSharedPreferences(STORAGE, Context.MODE_PRIVATE)
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
        .build()
}

fun provideMoshiAdapter(): JsonAdapter<UserLocationDTO> {
    return provideMoshi().adapter(UserLocationDTO::class.java)
}

fun provideGeolocationApi(): GeoLocationApiService {
    return provideRetrofit()
        .create(GeoLocationApiService::class.java)
}