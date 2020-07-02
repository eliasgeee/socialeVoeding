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
import com.socialevoeding.data.datasources.remote.UserLocationRemoteDataSource
import com.socialevoeding.data.dtos.local.device.UserLocationDTO
import com.socialevoeding.framework_androidsdk.device.gps.LocationGPSDataSource
import com.socialevoeding.framework_androidsdk.local.room.dao.CategoryDao
import com.socialevoeding.framework_androidsdk.local.room.dao.PlaceDao
import com.socialevoeding.framework_androidsdk.local.room.database.LocalDb
import com.socialevoeding.framework_androidsdk.local.room.database.getDatabase
import com.socialevoeding.framework_androidsdk.local.room.datasources.RoomCategoryDataSource
import com.socialevoeding.framework_androidsdk.local.room.datasources.RoomPlaceDataSource
import com.socialevoeding.framework_androidsdk.local.sharedPreferences.SharedPrefUserLocationDataSource
import com.socialevoeding.framework_androidsdk.remote.ServiceFactory
import com.socialevoeding.framework_androidsdk.remote.ServiceProvider
import com.socialevoeding.framework_androidsdk.remote.ServiceProviderImpl
import com.socialevoeding.framework_androidsdk.remote.UserLocationRemoteDataSourceImpl
import com.socialevoeding.framework_androidsdk.remote.retrofit.apiServices.GeoLocationApiService
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val STORAGE = "com.socialevoeding.bap.USERLOCATION"

val frameworkAndroidModule = module {

    single { getDatabase (androidContext()) }

    single { provideCategoryDao(get()) }
    single { providePlaceDao(get()) }

    single { RoomCategoryDataSource(get()) as CategoryLocalDataSource }
    single { RoomPlaceDataSource(get()) as PlaceLocalDataSource }
    single { SharedPrefUserLocationDataSource(get(), get()) as UserLocationCacheDataSource }
    single { LocationGPSDataSource(get()) as CurrentLocationDataSource }

//    single { provideMoshiAdapterLocationDTO() }

    single { provideLocationManager(androidContext()) }

    single { getSharedPrefs(androidContext()) }

  //  single { provideMoshi() }

    single { Environment(NetworkConfig.BASE_URL_GEOLOCATION) }

    single {
        ServiceFactory(
            MoshiConverterFactory.create(provideMoshi()).asLenient(),
            CoroutineCallAdapterFactory(),
            get(),
            get()
        )
    }

    single { ServiceProviderImpl(get()) }

    single { UserLocationRemoteDataSourceImpl(get()) as UserLocationRemoteDataSource }

    single { provideMoshi() }

    single { provideRetrofitClient() }
}

fun provideRetrofitClient() : OkHttpClient{
    return OkHttpClient().newBuilder().build()
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
