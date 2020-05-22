package com.socialevoeding.bap.data.repositories

import android.location.Location
import com.socialevoeding.bap.domain.repositories.LocationRepository
import com.socialevoeding.bap.location.GPSTracker

class LocationRepositoryImpl(private val gpsTracker: GPSTracker) : LocationRepository {
    override suspend fun getCurrentLocation() : String {
        return gpsTracker.getCurrentLocation()
    }

    override suspend fun initializeGPSTracker(): String{
        return gpsTracker.startGPSTracker()
    }
}