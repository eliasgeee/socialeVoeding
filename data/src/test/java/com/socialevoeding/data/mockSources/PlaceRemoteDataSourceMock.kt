package com.socialevoeding.data.mockSources

import com.socialevoeding.data.datasources.remote.PlaceRemoteDataSource
import com.socialevoeding.data.dtos.remote.NetworkPlace

class PlaceRemoteDataSourceMock(private val placeRemoteDataSourceMockImplementation: PlaceRemoteDataSourceMockImplementation) :
    PlaceRemoteDataSource {
    override suspend fun getPlaces(queryString: String, currenPlaceName: String): List<NetworkPlace> {
        return placeRemoteDataSourceMockImplementation.getPlaces(queryString = queryString, currenPlaceName = currenPlaceName)
    }
}

class PlaceRemoteDataSourceMockImplementation {
    suspend fun getPlaces(queryString: String, currenPlaceName: String): List<NetworkPlace> {
        return emptyList()
    }
}