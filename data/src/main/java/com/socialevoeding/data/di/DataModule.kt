package com.socialevoeding.data.di

import com.socialevoeding.data.CategoryDataProvider
import com.socialevoeding.data.mappers.*
import com.socialevoeding.data.repositories.CategoryRepositoryImpl
import com.socialevoeding.data.repositories.UserLocationRepositoryImpl
import com.socialevoeding.data.repositories.PlaceRepositoryImpl
import com.socialevoeding.domain.repositories.CategoryRepository
import com.socialevoeding.domain.repositories.UserLocationRepository
import com.socialevoeding.domain.repositories.PlaceRepository
import org.koin.dsl.module

val dataModule = module {

    single { PlaceRepositoryImpl(
        get(),
        get(),
        get(),
        get()
    ) as PlaceRepository }
    single { CategoryRepositoryImpl(get(), get(), get()) as CategoryRepository }
    single { UserLocationRepositoryImpl(get(), get(), get(), get(), get()) as UserLocationRepository }

    single { provideDatabasePlaceMapper() }
    single { provideNetworkPlaceMapper() }
    single { provideCategoryProvider() }
    single { provideDatabaseCategoryMapper() }
    single { provideCacheLocationItemMapper() }
    single { provideDeviceCoordinatesMapper() }
}

fun provideCacheLocationItemMapper(): CacheLocationItemMapper {
    return CacheLocationItemMapper
}

fun provideDeviceCoordinatesMapper(): DeviceCoordinatesMapper {
    return DeviceCoordinatesMapper
}

fun provideDatabasePlaceMapper(): PlacePlaceMapper {
    return PlacePlaceMapper
}

fun provideNetworkPlaceMapper(): NetworkPlaceMapper {
    return NetworkPlaceMapper
}

fun provideDatabaseCategoryMapper(): DatabaseCategoryMapperFacade {
    return DatabaseCategoryMapperFacade
}

fun provideCategoryProvider(): CategoryDataProvider {
    return CategoryDataProvider
}