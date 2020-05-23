package com.socialevoeding.bap.restful.apiServices

import retrofit2.http.GET

interface GeoLocationApiService {

    @GET("")
    fun getCurrentGeoLocation(){

    }
}