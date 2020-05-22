package com.socialevoeding.bap.usecases

import com.socialevoeding.bap.domain.repositories.LocationRepository
import com.socialevoeding.bap.usecases.base.ErrorMapper
import com.socialevoeding.bap.usecases.base.ForeGroundUseCase
import com.socialevoeding.bap.usecases.base.UseCase

class StartGPSTrackerUseCase (
    errorMapper: ErrorMapper,
    private val locationRepository: LocationRepository
) : ForeGroundUseCase<String>(errorMapper) {
    override suspend fun executeOnForeground(): String {
        return locationRepository.initializeGPSTracker()
    }
}