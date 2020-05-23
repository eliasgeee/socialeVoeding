package com.socialevoeding.bap.usecases

import com.socialevoeding.bap.domain.model.LocationModel
import com.socialevoeding.bap.domain.repositories.GPSRepository
import com.socialevoeding.bap.usecases.base.ErrorMapper
import com.socialevoeding.bap.usecases.base.ForeGroundUseCase

class StartGPSTrackerUseCase (
    errorMapper: ErrorMapper,
    private val GPSRepository: GPSRepository
) : ForeGroundUseCase<LocationModel>(errorMapper) {
    override suspend fun executeOnForeground(): LocationModel {
        return GPSRepository.initializeGPSTracker()
    }
}