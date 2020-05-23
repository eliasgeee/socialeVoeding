package com.socialevoeding.bap.restful

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkConfig {

  //  private const val BASE_URL = "https://api.foursquare.com/"
    private const val BASE_URL = "http://api.serpstack.com/"
    private const val BASE_URL_GEOLOCATION = ""

    fun getFoursquareBaseUrl() : String {
        return BASE_URL
    }

    fun getGeolocationBaseUrl() : String {
        return BASE_URL_GEOLOCATION
    }

}
