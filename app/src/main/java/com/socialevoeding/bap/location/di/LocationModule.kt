package com.socialevoeding.bap.location.di

import com.socialevoeding.bap.location.GPSTracker
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val locationModule = module {
    single { GPSTracker(get()) }
}
