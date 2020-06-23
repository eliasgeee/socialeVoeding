package com.socialevoeding.data.datasources.local.database

import com.socialevoeding.data.dtos.local.database.PlaceEntity

interface PlaceLocalDataSource  {
    fun insertPlaces(places : List<PlaceEntity>)
    fun getPlaces() : List<PlaceEntity>
    fun insertAll(places: List<PlaceEntity>)
}