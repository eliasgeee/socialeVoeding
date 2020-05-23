package com.socialevoeding.bap.restful.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.socialevoeding.bap.restful.NetworkConfig
import com.socialevoeding.bap.restful.apiServices.GeoLocationApiService
import com.socialevoeding.bap.restful.apiServices.PlacesApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single { provideRetrofit(NetworkConfig.getFoursquareBaseUrl()) }
    single { providePlacesApi() }
    single { provideGeolocationApi() }
}

fun provideRetrofit(baseUrl : String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(provideMoshi()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
}

fun provideRetrofitGeoLocation(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(NetworkConfig.getGeolocationBaseUrl())
        .addConverterFactory(MoshiConverterFactory.create(provideMoshi()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
}

fun provideMoshi() : Moshi{
    return Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}

fun providePlacesApi() : PlacesApiService {
    return provideRetrofit(NetworkConfig.getFoursquareBaseUrl())
        .create(PlacesApiService::class.java)
}

fun provideGeolocationApi() : GeoLocationApiService{
    return provideRetrofit(NetworkConfig.getGeolocationBaseUrl())
        .create(GeoLocationApiService::class.java)
}