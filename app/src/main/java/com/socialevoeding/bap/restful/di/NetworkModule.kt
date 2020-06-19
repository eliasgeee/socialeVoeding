package com.socialevoeding.bap.restful.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.socialevoeding.bap.restful.NetworkConfig
import com.socialevoeding.bap.restful.apiServices.GeoLocationApiService
import com.socialevoeding.bap.restful.apiServices.PlacesApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module
import pl.droidsonroids.retrofit2.JspoonConverterFactory
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ConverterFactoryType{
    const val MOSHI_FACTORY = 1
    const val JSPOON_FACTORY = 2
}

val networkModule = module {
    single { provideRetrofit(NetworkConfig.getGoogleBaseUrl(),ConverterFactoryType.MOSHI_FACTORY) }
    single { providePlacesApi() }
    single { provideGeolocationApi() }
}

fun provideRetrofit(baseUrl : String, factoryType : Int): Retrofit {
    var factory : Any? = null
    if(factoryType == ConverterFactoryType.MOSHI_FACTORY){
        factory = MoshiConverterFactory.create(provideMoshi())
    }
    else if(factoryType == ConverterFactoryType.JSPOON_FACTORY){
        factory = JspoonConverterFactory.create()
    }
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(JspoonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
}

fun provideMoshi() : Moshi{
    return Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}

fun providePlacesApi() : PlacesApiService {
    return provideRetrofit(NetworkConfig.getGoogleBaseUrl(), ConverterFactoryType.JSPOON_FACTORY)
        .create(PlacesApiService::class.java)
}

fun provideGeolocationApi() : GeoLocationApiService{
    return provideRetrofit(NetworkConfig.getGeolocationBaseUrl(), ConverterFactoryType.MOSHI_FACTORY)
        .create(GeoLocationApiService::class.java)
}

