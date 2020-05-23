package com.socialevoeding.bap.usecases

import com.socialevoeding.bap.domain.repositories.LocationRepository
import com.socialevoeding.bap.usecases.base.ErrorMapper
import com.socialevoeding.bap.usecases.base.ForeGroundUseCase

class StopGPSTrackerUseCase (
    errorMapper: ErrorMapper,
    private val locationRepository: LocationRepository
) : ForeGroundUseCase<Boolean>(errorMapper) {
    override suspend fun executeOnForeground(): Boolean {
        return locationRepository.stopGPSTracker()
    }
}