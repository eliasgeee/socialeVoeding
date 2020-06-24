package com.socialevoeding.framework_shared.di

import com.socialevoeding.data.datasources.remote.PlaceRemoteDataSource
import com.socialevoeding.framework_shared.remote.jsoup.SoupPlaceDataSource
import org.koin.dsl.module

val frameworkSharedModule = module {
    single { SoupPlaceDataSource() as PlaceRemoteDataSource }
}