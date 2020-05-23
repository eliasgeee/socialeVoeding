package com.socialevoeding.bap.gps

import com.socialevoeding.bap.domain.model.LocationModel
import com.socialevoeding.bap.domain.repositories.GPSRepository

class GPSRepositoryImpl(private val gpsTracker: GPSTracker) : GPSRepository {
    override suspend fun getCurrentLocation() : LocationModel {
        return gpsTracker.getCurrentLocation()
    }

    override suspend fun initializeGPSTracker(): LocationModel {
        return gpsTracker.startGPSTracker()
    }

    override suspend fun stopGPSTracker(): Boolean {
        return gpsTracker.stopUsingGPS()
    }
}