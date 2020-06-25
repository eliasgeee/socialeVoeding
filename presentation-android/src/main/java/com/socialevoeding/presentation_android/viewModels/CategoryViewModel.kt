package com.socialevoeding.presentation_android.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.socialevoeding.presentation_android.mappers.CategoryViewItemMapper
import com.socialevoeding.presentation_android.viewItems.CategoryViewItem
import com.socialevoeding.usecases.categorieUseCases.GetCategoriesUseCase
import com.socialevoeding.usecases.categorieUseCases.InitCategoriesUseCase

class CategoryViewModel(
    private val initCategoriesUseCase: InitCategoriesUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private var _categories = MutableLiveData<List<CategoryViewItem>>()
    val categories: LiveData<List<CategoryViewItem>>
        get() = _categories

    private var _goToCategory = MutableLiveData<CategoryViewItem>()
    val goToCategory: LiveData<CategoryViewItem>
        get() = _goToCategory

    init {
        getCategoriesUseCase.execute {
            onComplete {
                _categories.postValue(CategoryViewItemMapper.mapToViewItems(it.data))
            }
        }
    }

    fun goToCategory(category: CategoryViewItem) {
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