package com.socialevoeding.bap.ui.di

import com.socialevoeding.bap.ui.main.GPSTrackerViewModel
import com.socialevoeding.bap.ui.categories.CategoryViewModel
import com.socialevoeding.bap.ui.placedetails.PlaceDetailsViewModel
import com.socialevoeding.bap.ui.places.PlacesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { CategoryViewModel(get(), get()) }
    viewModel { PlacesViewModel(get(), get(), get()) }
    viewModel { PlaceDetailsViewModel() }
    viewModel {
        GPSTrackerViewModel()
    }
}