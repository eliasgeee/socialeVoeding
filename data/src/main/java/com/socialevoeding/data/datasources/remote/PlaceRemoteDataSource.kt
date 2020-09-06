package com.socialevoeding.data.datasources.remote

import com.socialevoeding.data.dtos.remote.NetworkPlace
import kotlinx.coroutines.flow.Flow

interface PlaceRemoteDataSource {
    suspend fun getPlaces(queryString: String, currentPlaceName: String): Flow<List<NetworkPlace>>
}