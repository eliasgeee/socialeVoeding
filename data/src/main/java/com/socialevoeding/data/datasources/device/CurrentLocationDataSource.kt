package com.socialevoeding.data.datasources.device

import com.socialevoeding.data.dtos.local.device.CoordinatesDTO

interface CurrentLocationDataSource {
    fun getCurrentLocation() : CoordinatesDTO
}