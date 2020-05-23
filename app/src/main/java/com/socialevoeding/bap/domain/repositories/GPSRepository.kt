package com.socialevoeding.bap.domain.repositories

import android.location.Location
import com.socialevoeding.bap.domain.model.LocationModel

interface GPSRepository {
    suspend fun getCurrentLocation() : LocationModel
    suspend fun initializeGPSTracker() : LocationModel
    suspend fun stopGPSTracker() : Boolean
}