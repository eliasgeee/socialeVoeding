package com.socialevoeding.presentation_android.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.socialevoeding.presentation_android.ViewItem
import com.socialevoeding.presentation_android.mappers.CategoryViewItemMapper
import com.socialevoeding.usecases.categorieUseCases.GetCategoriesUseCase
import com.socialevoeding.usecases.categorieUseCases.InitCategoriesUseCase

class CategoryViewModel(
    private val initCategoriesUseCase: InitCategoriesUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private var _categories = MutableLiveData<List<ViewItem.CategoryViewItem>>()
    val categories: LiveData<List<ViewItem.CategoryViewItem>>
        get() = _categories

    private var _goToCategory = MutableLiveData<ViewItem.CategoryViewItem>()
    val goToCategory: LiveData<ViewItem.CategoryViewItem>
        get() = _goToCategory

    init {
        getCategoriesUseCase.execute {
            onComplete {
                _categories.postValue(it.data.map { cat -> CategoryViewItemMapper.mapToViewItem(cat) })
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