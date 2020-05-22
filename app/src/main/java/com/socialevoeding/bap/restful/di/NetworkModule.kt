package com.socialevoeding.bap.restful.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.socialevoeding.bap.restful.NetworkConfig
import com.socialevoeding.bap.restful.apiServices.PlacesApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single { provideRetrofit() }
    single { providePlacesApi() }
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(NetworkConfig.getFoursquareBaseUrl())
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
    return provideRetrofit()
        .create(PlacesApiService::class.java)
}