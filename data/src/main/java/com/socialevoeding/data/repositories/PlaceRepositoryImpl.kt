package com.socialevoeding.data.repositories

import com.socialevoeding.data.datasources.local.database.PlaceLocalDataSource
import com.socialevoeding.data.datasources.remote.PlaceRemoteDataSource
import com.socialevoeding.data.mappers.DatabasePlaceMapper
import com.socialevoeding.data.mappers.NetworkPlaceMapper
import com.socialevoeding.domain.model.Place
import com.socialevoeding.domain.model.UserLocation
import com.socialevoeding.domain.repositories.PlaceRepository

class PlaceRepositoryImpl(private val placeRemoteDataSource: PlaceRemoteDataSource, private val placeLocalDataSource: PlaceLocalDataSource) :
    PlaceRepository {

    override suspend fun refreshPlaces(
        userLocation: UserLocation,
        currentCategoryName: String
    ) {
        val places = placeRemoteDataSource.getPlaces(currentCategoryName, userLocation.cityName)
        placeLocalDataSource.clear()
        placeLocalDataSource.insertAll(DatabasePlaceMapper.mapToEntities(NetworkPlaceMapper.mapToListOfDomainObjects(places)))
    }

    override suspend fun getPlacesFromLocalDatabase(): MutableList<Place> {
        return DatabasePlaceMapper.mapFromEntities(placeLocalDataSource.getPlaces()).toMutableList()
    }

    override suspend fun insertPlacesIntoDatabase(places: List<Place>) {
        return placeLocalDataSource.insertPlaces(DatabasePlaceMapper.mapToEntities(places))
    }
}
