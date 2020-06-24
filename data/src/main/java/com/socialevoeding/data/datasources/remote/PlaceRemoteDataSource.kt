package com.socialevoeding.data.datasources.remote

import com.socialevoeding.data.dtos.remote.NetworkPlace

interface PlaceRemoteDataSource {
    suspend fun getPlaces(queryString: String, currenPlaceName: String): List<NetworkPlace>
}