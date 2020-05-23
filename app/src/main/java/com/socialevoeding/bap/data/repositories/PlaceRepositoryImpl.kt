package com.socialevoeding.bap.data.repositories

import com.socialevoeding.bap.data.dao.PlaceDao
import com.socialevoeding.bap.data.entities.PlaceEntity
import com.socialevoeding.bap.data.entities.asDomainModel
import com.socialevoeding.bap.data.entities.toEntity
import com.socialevoeding.bap.domain.model.LocationModel
import com.socialevoeding.bap.domain.model.Place
import com.socialevoeding.bap.domain.repositories.PlaceRepository
import com.socialevoeding.bap.restful.apiServices.PlacesApiService
import com.socialevoeding.bap.restful.dataTransferObjects.PlaceDTO
import com.socialevoeding.bap.restful.dataTransferObjects.asDatabaseModel
import java.lang.Exception

class PlaceRepositoryImpl(private val placeDao: PlaceDao, private val placesApiService: PlacesApiService) : PlaceRepository {

   override suspend fun refreshPlaces(currentLocationModel : LocationModel, currentCategoryName : String, currentQueryNames : List<String>) : Boolean{
       val categoryID = 1
       var places : PlaceDTO? = null
       var placeEntities = ArrayList<PlaceEntity>()

       try {
           for (query in currentQueryNames){
               places = placesApiService.getPlacesAsync(
                   queryString = "$query${currentLocationModel.cityName}"
               ).await()
               if(places.local_results != null)
               placeEntities.addAll(places.asDatabaseModel(places.local_results!!, categoryID, currentLocationModel))
           }
       }
       catch (e : Exception){
           e.message
       }
           placeDao.insertAll(*placeEntities.toTypedArray())
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