package com.socialevoeding.bap.ui.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.socialevoeding.bap.domain.model.Category
import com.socialevoeding.bap.usecases.GetCategoriesFromLocalDatabaseUseCase
import com.socialevoeding.bap.usecases.InsertCategoriesIntoLocalDatabaseUseCase

class CategoryViewModel(private val insertCategoriesIntoLocalDatabaseUseCase: InsertCategoriesIntoLocalDatabaseUseCase,
                        private val getCategoriesFromLocalDatabaseUseCase: GetCategoriesFromLocalDatabaseUseCase) : ViewModel() {

    private var _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>>
        get() = _categories

    private var _goToCategory = MutableLiveData<Category>()
    val goToCategory: LiveData<Category>
        get() = _goToCategory

    init {
        insertCategoriesIntoLocalDatabaseUseCase.execute {
            onComplete {
                getCategoriesFromLocalDatabaseUseCase.execute {
                    onComplete {
                        _categories.postValue(it)
                    }
                }
            }
        }
    }

    fun goToCategory(category: Category) {
        _goToCategory.value = category
    }

    fun onCategoryNavigated() {
        _goToCategory.value = null
    }

    override fun onCleared() {
        super.onCleared()
        insertCategoriesIntoLocalDatabaseUseCase.unsubscribe()
        getCategoriesFromLocalDatabaseUseCase.unsubscribe()
    }
}