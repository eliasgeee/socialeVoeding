package com.socialevoeding.bap.data.repositories

import com.socialevoeding.bap.data.dao.PlaceDao
import com.socialevoeding.bap.data.entities.PlaceEntity
import com.socialevoeding.bap.data.entities.asDomainModel
import com.socialevoeding.bap.data.entities.toEntity
import com.socialevoeding.domain.model.LocationModel
import com.socialevoeding.domain.model.Place
import com.socialevoeding.domain.repositories.PlaceRepository
import com.socialevoeding.bap.restful.apiServices.PlacesApiService
import com.socialevoeding.bap.restful.dataTransferObjects.PlaceDTO
import com.socialevoeding.bap.restful.dataTransferObjects.asDatabaseModel
import com.socialevoeding.domain.model.Result
import kotlin.Exception

class PlaceRepositoryImpl(private val placeDao: PlaceDao, private val placesApiService: PlacesApiService) :
    PlaceRepository {

    override suspend fun refreshPlaces(
        currentLocationModel: LocationModel,
        currentCategoryName: String,
        currentQueryNames: List<String>
    ) {
          val places = placesApiService.getPlacesAsync(
                    queryString = "Voedselbank + ${currentLocationModel.cityName}/@${currentLocationModel.latitude},${currentLocationModel.longitude},10z"
                ).await()

        println(places)

        placeDao.insertAll()
    }

    override suspend fun getPlacesFromLocalDatabase(): MutableList<Place> {
        return placeDao.getPlaces().asDomainModel().toMutableList()
    }

    override suspend fun insertPlacesIntoDatabase(places: List<Place>) {
        return placeDao.insertAll(*places.toEntity())
    }
}