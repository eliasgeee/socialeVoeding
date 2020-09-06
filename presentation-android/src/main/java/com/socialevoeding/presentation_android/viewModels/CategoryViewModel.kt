package com.socialevoeding.presentation_android.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.socialevoeding.presentation_android.ViewItem
import com.socialevoeding.presentation_android.ViewState
import com.socialevoeding.presentation_android.mappers.CategoryViewItemMapper
import com.socialevoeding.usecases.categorieUseCases.GetCategoriesUseCase
import com.socialevoeding.usecases.categorieUseCases.InitCategoriesUseCase
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val initCategoriesUseCase: InitCategoriesUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private var _goToCategory = MutableLiveData<ViewItem.CategoryViewItem>()
    val goToCategory: LiveData<ViewItem.CategoryViewItem>
        get() = _goToCategory

    private var _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState>
        get() = _viewState

    init {
        initCategoriesUseCase.execute {
            _viewState.value = ViewState.Loading()
            onComplete { loadCategories() }
            onError { ViewState.Error(it.throwable.message!!) }
        }
    }

    private fun loadCategories() {
        viewModelScope.launch {
            getCategoriesUseCase.execute {
                onComplete {
                    _viewState.postValue(ViewState.Succes(it.data.map { cat -> CategoryViewItemMapper.mapToViewItem(cat) })) }
                onError { _viewState.value = ViewState.Error(it.throwable.message!!) }
            }
        }
    }

    fun goToCategory(category: ViewItem.CategoryViewItem) {
        _goToCategory.value = category
    }

    fun onCategoryNavigated() {
        _goToCategory.value = null
    }

    override fun onCleared() {
        super.onCleared()
        initCategoriesUseCase.unsubscribe()
        getCategoriesUseCase.unsubscribe()
    }
}