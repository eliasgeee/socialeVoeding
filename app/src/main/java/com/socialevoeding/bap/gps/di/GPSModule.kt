package com.socialevoeding.bap.gps.di

import com.socialevoeding.bap.gps.GPSTracker
import org.koin.dsl.module

val gpsModule = module {
    single { GPSTracker(get()) }
}
