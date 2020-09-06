package com.socialevoeding.data.repositories

import com.socialevoeding.data.datasources.local.database.PlaceLocalDataSource
import com.socialevoeding.data.datasources.remote.PlaceRemoteDataSource
import com.socialevoeding.data.dtos.remote.NetworkPlace
import com.socialevoeding.data.mappers.PlacePlaceMapper
import com.socialevoeding.data.mappers.NetworkPlaceMapper
import com.socialevoeding.domain.model.place.Place
import com.socialevoeding.domain.model.UserLocation
import com.socialevoeding.domain.repositories.PlaceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

class PlaceRepositoryImpl(
    private val placeRemoteDataSource: PlaceRemoteDataSource,
    private val placeLocalDataSource: PlaceLocalDataSource,
    private val databasePlaceMapper: PlacePlaceMapper,
    private val networkPlaceMapper: NetworkPlaceMapper
) : PlaceRepository {

    override suspend fun refreshPlaces(userLocation: UserLocation, currentCategoryName: String) {
        clearLocalDataSource()
        getPlacesFromRemoteDataSource(currentCategoryName, userLocation.cityName).collect { places ->
            insertRemotePlacesIntoDatabase(places)
        }
    }

    private suspend fun getPlacesFromRemoteDataSource(currentCategoryName: String, cityName: String): Flow<List<NetworkPlace>> {
            return placeRemoteDataSource.getPlaces(currentCategoryName, cityName)
    }

    override suspend fun getPlacesFromLocalDatabase(): Flow<List<Place>> =
        //flow { PlacePlaceMapper.mapFromEntities(placeLocalDataSource.getPlaces()).ma }
        placeLocalDataSource.getPlaces().map { PlacePlaceMapper.mapFromEntities(it) }

    override suspend fun insertPlacesIntoDatabase(places: List<Place>) = placeLocalDataSource.insertAll(databasePlaceMapper.mapToEntities(places))

    private suspend fun clearLocalDataSource() = placeLocalDataSource.clear()

    private suspend fun insertRemotePlacesIntoDatabase(networkPlaces: List<NetworkPlace>) {
        placeLocalDataSource.insertAll(
            databasePlaceMapper.mapToEntities(networkPlaceMapper.mapToListOfDomainObjects(networkPlaces))
        )
    }
}
