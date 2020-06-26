package com.socialevoeding.presentation_android.di

import com.socialevoeding.presentation_android.viewModels.InitViewModel
import com.socialevoeding.presentation_android.viewModels.CategoryViewModel
import com.socialevoeding.presentation_android.viewModels.PlaceDetailsViewModel
import com.socialevoeding.presentation_android.viewModels.PlacesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        CategoryViewModel(
            get(),
            get()
        )
    }
    viewModel {
        PlacesViewModel(
            get(),
            get()
        )
    }
    viewModel { PlaceDetailsViewModel() }
    viewModel {
        InitViewModel(get(), get(), get(), get(), get(), get())
    }
}