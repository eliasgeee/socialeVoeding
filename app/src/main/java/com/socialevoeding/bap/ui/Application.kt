package com.socialevoeding.bap.ui

import android.app.Application
import android.content.Context
import android.location.LocationManager
import com.socialevoeding.bap.data.di.dataModule
import com.socialevoeding.bap.location.di.locationModule
import com.socialevoeding.bap.ui.di.appModule
import com.socialevoeding.bap.restful.di.networkModule
import com.socialevoeding.bap.usecases.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Initializing Koin for dependency injection
 */
class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Application)
            modules(dataModule, appModule, useCaseModule, networkModule, locationModule)
        }
    }
}