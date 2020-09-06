package com.socialevoeding.presentation_android.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.socialevoeding.presentation_android.ViewState
import com.socialevoeding.presentation_android.mappers.base.ViewItemMapper
import com.socialevoeding.usecases.base.UseCase

abstract class BaseViewModel<V, T>(val useCase: UseCase<T>) : ViewModel() {

    private var _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState>
        get() = _viewState

    fun loadItems(mapper: ViewItemMapper<V, T>) {
        useCase.execute {
            _viewState.value = ViewState.Loading()

            onComplete {
                _viewState.postValue(ViewState.Succes(mapper.mapToViewItem(it.data)))
            }

            onError {
                _viewState.value = ViewState.Error(it.throwable.message!!)
            }
        }
    }
}