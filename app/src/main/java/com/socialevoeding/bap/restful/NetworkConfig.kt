package com.socialevoeding.bap.restful

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkConfig {

    private const val BASE_URL_PLACES = "https://www.google.com/maps/"
    private const val BASE_URL_GEOLOCATION = "https://nominatim.openstreetmap.org/"

    fun getGoogleBaseUrl() : String {
        return BASE_URL_PLACES
    }

    fun getGeolocationBaseUrl() : String {
        return BASE_URL_GEOLOCATION
    }

}
