package com.socialevoeding.framework_androidsdk.di

import android.content.Context
import android.content.SharedPreferences
import android.location.LocationManager
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.socialevoeding.data.datasources.device.CurrentLocationDataSource
import com.socialevoeding.data.datasources.local.cache.UserLocationCacheDataSource
import com.socialevoeding.data.datasources.local.database.CategoryLocalDataSource
import com.socialevoeding.data.datasources.local.database.PlaceLocalDataSource
import com.socialevoeding.data.datasources.remote.Environment
import com.socialevoeding.data.datasources.remote.NetworkConfig
import com.socialevoeding.data.datasources.remote.PlaceRemoteDataSource
import com.socialevoeding.data.datasources.remote.UserLocationRemoteDataSource
import com.socialevoeding.framework_androidsdk.device.gps.LocationGPSDataSource
import com.socialevoeding.framework_androidsdk.local.room.dao.CategoryDao
import com.socialevoeding.framework_androidsdk.local.room.dao.PlaceDao
import com.socialevoeding.framework_androidsdk.local.room.database.LocalDb
import com.socialevoeding.framework_androidsdk.local.room.database.getDatabase
import com.socialevoeding.framework_androidsdk.local.room.datasources.RoomCategoryDataSource
import com.socialevoeding.framework_androidsdk.local.room.datasources.RoomPlaceDataSource
import com.socialevoeding.framework_androidsdk.local.sharedPreferences.SharedPrefUserLocationDataSource
import com.socialevoeding.framework_androidsdk.remote.datasources.PlaceRemoteDataSourceImpl
import com.socialevoeding.framework_androidsdk.remote.retrofit.creation.ServiceFactory
import com.socialevoeding.framework_androidsdk.remote.retrofit.creation.ServiceProviderImpl
import com.socialevoeding.framework_androidsdk.remote.datasources.UserLocationRemoteDataSourceImpl
import com.socialevoeding.framework_androidsdk.remote.retrofit.converters.ConverterTypes
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.time.Duration
import java.util.concurrent.TimeUnit

private const val STORAGE = "com.socialevoeding.bap.USERLOCATION"

val frameworkAndroidModule = module {

    single { getDatabase (androidContext()) }

    single { provideCategoryDao(get()) }
    single { providePlaceDao(get()) }

    single { RoomCategoryDataSource(get()) as CategoryLocalDataSource }
    single { RoomPlaceDataSource(get()) as PlaceLocalDataSource }
    single { SharedPrefUserLocationDataSource(get(), get()) as UserLocationCacheDataSource }
    single { LocationGPSDataSource(get()) as CurrentLocationDataSource }
    single { provideOkHttpClient() }

//    single { provideMoshiAdapterLocationDTO() }

    single { provideLocationManager(androidContext()) }

    single { getSharedPrefs(androidContext()) }

  //  single { provideMoshi() }

    single(named("geolocation_env")) { Environment(NetworkConfig.BASE_URL_GEOLOCATION) }
    single(named("places_search_env")) {Environment(NetworkConfig.BASE_URL_SEARCH)}

    single(named("geolocation_service")) {
        ServiceFactory(
            ConverterTypes.provideConverters(),
            CoroutineCallAdapterFactory(),
            get(),
            get(named("geolocation_env"))
        )
    }

    single(named("places_search_service")) {
        ServiceFactory(
            ConverterTypes.provideConverters(),
            CoroutineCallAdapterFactory(),
            get(),
            get(named("places_search_env"))
        )
    }

    single(named("geolocation_serviceProv")) {
        ServiceProviderImpl(
            get(named("geolocation_service"))
        )
    }

    single(named("places_search_serviceProv")) {
        ServiceProviderImpl(
            get(named("places_search_service"))
        )
    }

    single { UserLocationRemoteDataSourceImpl(
        get(named("geolocation_serviceProv"))
    ) as UserLocationRemoteDataSource }

    single { provideMoshi() }

    single { PlaceRemoteDataSourceImpl(
        get(named("places_search_serviceProv"))
    ) as PlaceRemoteDataSource }
}

fun provideOkHttpClient() : OkHttpClient {
    return OkHttpClient()
        .newBuilder()
        .build()
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

fun providePlaceDao(db: LocalDb): PlaceDao {
    return db.placeDao
}

fun provideMoshi(): Moshi {
    return Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}

/*fun provideMoshiAdapterLocationDTO(): JsonAdapter<UserLocationDTO> {
    return provideMoshi().adapter(UserLocationDTO::class.java)
}*/
