package com.socialevoeding.presentation_android

import androidx.lifecycle.MutableLiveData

//used to update the view
sealed class ViewState{
    class ShowLoading(val img : String) : ViewState()
    //can give specific error messages when adding an error model and mapper to Result.Failure in util_models
    class ShowErrorMessage(val errorMessage : String, val errorImg: String) : ViewState()
    class ShowSucces<T>(val succes : T)
}

val viewState: MutableLiveData<ViewState> = MutableLiveData()