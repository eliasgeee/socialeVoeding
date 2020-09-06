package com.socialevoeding.framework_androidsdk.remote.retrofit.creation

import com.socialevoeding.framework_androidsdk.remote.retrofit.apiServices.GeoLocationApiService
import com.socialevoeding.framework_androidsdk.remote.retrofit.apiServices.PlacesApiService

interface ServiceProvider {
    fun getGeolocationService(): GeoLocationApiService
    fun getPlacesService(): PlacesApiService
}