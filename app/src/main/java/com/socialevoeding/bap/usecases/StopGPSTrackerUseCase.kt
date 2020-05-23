package com.socialevoeding.bap.usecases

import com.socialevoeding.bap.domain.repositories.GPSRepository
import com.socialevoeding.bap.usecases.base.ErrorMapper
import com.socialevoeding.bap.usecases.base.ForeGroundUseCase

class StopGPSTrackerUseCase (
    errorMapper: ErrorMapper,
    private val GPSRepository: GPSRepository
) : ForeGroundUseCase<Boolean>(errorMapper) {
    override suspend fun executeOnForeground(): Boolean {
        return GPSRepository.stopGPSTracker()
    }
}