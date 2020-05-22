package com.socialevoeding.bap.ui.di

import android.content.Context
import android.location.LocationManager
import androidx.core.content.ContextCompat.getSystemService
import com.socialevoeding.bap.ui.GPSTrackerViewModel
import com.socialevoeding.bap.ui.categories.CategoryViewModel
import com.socialevoeding.bap.ui.placedetails.PlaceDetailsViewModel
import com.socialevoeding.bap.ui.places.PlacesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { CategoryViewModel(get(), get()) }
    viewModel { PlacesViewModel(get(), get()) }
    viewModel { PlaceDetailsViewModel() }
    viewModel { GPSTrackerViewModel(get()) }
}