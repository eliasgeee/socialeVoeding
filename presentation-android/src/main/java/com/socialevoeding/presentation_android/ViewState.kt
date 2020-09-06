package com.socialevoeding.presentation_android

import androidx.lifecycle.MutableLiveData

// used to update the view
sealed class ViewState {
    class Loading : ViewState()
    // can give specific error messages when adding an error model and mapper to Result.Failure in util_models
    class Error(val errorMessage: String) : ViewState()
    class Succes<T>(val succes: T) : ViewState()
}

val viewState: MutableLiveData<ViewState> = MutableLiveData()