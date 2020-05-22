package com.socialevoeding.bap.domain.repositories

import android.location.Location

interface LocationRepository {
    suspend fun getCurrentLocation() : String
    suspend fun initializeGPSTracker() : String
}