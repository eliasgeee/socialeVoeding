package com.socialevoeding.bap.data.repositories

import com.socialevoeding.bap.data.dao.PlaceDao
import com.socialevoeding.bap.data.entities.PlaceEntity
import com.socialevoeding.bap.data.entities.asDomainModel
import com.socialevoeding.bap.data.entities.toEntity
import com.socialevoeding.bap.domain.model.Place
import com.socialevoeding.bap.domain.repositories.PlaceRepository
import com.socialevoeding.bap.restful.apiServices.PlacesApiService
import com.socialevoeding.bap.restful.dataTransferObjects.PlaceDTO
import com.socialevoeding.bap.restful.dataTransferObjects.asDatabaseModel
import java.lang.Exception

class PlaceRepositoryImpl(private val placeDao: PlaceDao, private val placesApiService: PlacesApiService) : PlaceRepository {

   override suspend fun refreshPlaces(currentLocationName : String, currentCategoryName : String, currentQueryNames : List<String>) : Boolean{
       var places : PlaceDTO? = null
       var placeEntities = ArrayList<PlaceEntity>()

       try {
           for (query in currentQueryNames){

           }
              places = placesApiService.getPlacesAsync(
                  intent = "checkin",
                  version = "20200520",
                  limit = 5,
                  latitudeLongitude = "51.106897,3.779542",
                  query = "voedselbank"
           ).await()
       }
       catch (e : Exception){
           e.message
       }
           placeDao.insertAll(*places!!.asDatabaseModel(places.response!!.venues!!))
       return true
   }

    override suspend fun getPlacesFromLocalDatabase(): MutableList<Place> {
        return placeDao.getPlaces().asDomainModel().toMutableList()
    }

    override suspend fun insertPlacesIntoDatabase(places: ArrayList<Place>): Boolean {
        placeDao.insertAll(*places.toEntity())
        return true
    }
}