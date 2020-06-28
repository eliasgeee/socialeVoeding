package com.socialevoeding.data.repositories

import com.socialevoeding.data.datasources.local.database.PlaceLocalDataSource
import com.socialevoeding.data.datasources.remote.PlaceRemoteDataSource
import com.socialevoeding.data.dtos.remote.NetworkPlace
import com.socialevoeding.data.mappers.PlacePlaceMapper
import com.socialevoeding.data.mappers.NetworkPlaceMapper
import com.socialevoeding.domain.model.place.Place
import com.socialevoeding.domain.model.UserLocation
import com.socialevoeding.domain.repositories.PlaceRepository

class PlaceRepositoryImpl(
    private val placeRemoteDataSource: PlaceRemoteDataSource,
    private val placeLocalDataSource: PlaceLocalDataSource,
    private val databasePlaceMapper: PlacePlaceMapper,
    private val networkPlaceMapper: NetworkPlaceMapper
) :
    PlaceRepository {

    override suspend fun refreshPlaces(
        userLocation: UserLocation,
        currentCategoryName: String
    ) {
        val places = getPlacesFromRemoteDataSource(currentCategoryName, userLocation.cityName)
        clearLocalDataSource()
        insertRemotePlacesIntoDatabase(places)
    }

    suspend fun getPlacesFromRemoteDataSource(currentCategoryName: String, cityName: String): List<NetworkPlace> {
        return placeRemoteDataSource.getPlaces(currentCategoryName, cityName)
    }

    override suspend fun getPlacesFromLocalDatabase(): MutableList<Place> {
        return PlacePlaceMapper.mapFromEntities(placeLocalDataSource.getPlaces()).toMutableList()
    }

    override suspend fun insertPlacesIntoDatabase(places: List<Place>) {
        return placeLocalDataSource.insertAll(databasePlaceMapper.mapToEntities(places))
    }

    suspend fun clearLocalDataSource() {
        placeLocalDataSource.clear()
    }

    suspend fun insertRemotePlacesIntoDatabase(networkPlaces: List<NetworkPlace>) {
        placeLocalDataSource.insertAll(
            databasePlaceMapper.mapToEntities(networkPlaceMapper.mapToListOfDomainObjects(networkPlaces))
        )
    }
}
