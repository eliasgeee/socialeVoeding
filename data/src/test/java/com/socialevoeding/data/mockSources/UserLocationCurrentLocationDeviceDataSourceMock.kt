package com.socialevoeding.data.mockSources

import com.socialevoeding.data.datasources.device.CurrentLocationDataSource
import com.socialevoeding.data.dtos.local.device.CoordinatesDTO

class UserLocationCurrentLocationDeviceDataSourceMock(
    private val userLocationCurrentLocationDeviceDataSourceMockImpl: UserLocationCurrentLocationDeviceDataSourceMockImpl
) : CurrentLocationDataSource {
    override fun getCurrentLocation(): CoordinatesDTO {
        return userLocationCurrentLocationDeviceDataSourceMockImpl.getCurrentLocation()
    }
}

class UserLocationCurrentLocationDeviceDataSourceMockImpl() {
    fun getCurrentLocation(): CoordinatesDTO {
        return CoordinatesDTO(420.0, 420.0)
    }
}