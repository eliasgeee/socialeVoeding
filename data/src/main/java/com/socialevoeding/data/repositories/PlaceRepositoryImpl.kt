package com.socialevoeding.data.repositories

import com.socialevoeding.data.datasources.local.database.PlaceLocalDataSource
import com.socialevoeding.data.datasources.remote.PlaceRemoteDataSource
import com.socialevoeding.data.dtos.remote.NetworkOpeningDay
import com.socialevoeding.data.mappers.DatabasePlaceMapper
import com.socialevoeding.data.mappers.NetworkPlaceMapper
import com.socialevoeding.data.mappers.base.DatabaseMapperFacade
import com.socialevoeding.data.mappers.base.NetworkMapper
import com.socialevoeding.domain.model.PlaceLocation
import com.socialevoeding.domain.model.Place
import com.socialevoeding.domain.repositories.PlaceRepository

//TODO
class PlaceRepositoryImpl(private val placeRemoteDataSource: PlaceRemoteDataSource, private val placeLocalDataSource: PlaceLocalDataSource) :
    PlaceRepository {

    override suspend fun refreshPlaces(
        currentPlaceLocation: PlaceLocation,
        currentCategoryName: String,
        currentQueryNames: List<String>
    ) {
       // insertPlacesIntoDatabase(placeRemoteDataSource.getPlaces(currentQueryNames.toString(), currentPlaceLocation.cityName))
    }

    override suspend fun getPlacesFromLocalDatabase(): MutableList<Place> {
        return DatabasePlaceMapper.mapFromEntities(placeLocalDataSource.getPlaces()).toMutableList()
    }

    override suspend fun insertPlacesIntoDatabase(places: List<Place>) {
        return placeLocalDataSource.insertPlaces(DatabasePlaceMapper.mapToEntities(places))
    }
}
