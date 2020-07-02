package com.socialevoeding.framework_androidsdk.remote

import com.socialevoeding.framework_androidsdk.remote.retrofit.apiServices.GeoLocationApiService

interface ServiceProvider {
    fun getGeolocationService() : GeoLocationApiService
}