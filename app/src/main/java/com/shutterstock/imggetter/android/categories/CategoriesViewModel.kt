package com.shutterstock.imggetter.android.categories

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shutterstock.imggetter.android.SingleLiveEvent

class CategoriesViewModel(
    controller: CategoriesController
) : ViewModel(), CategoriesView, LifecycleObserver {

    val categories: MutableLiveData<List<CategoryModel>> = MutableLiveData()
    val error: SingleLiveEvent<Int> = SingleLiveEvent()

    init {
        controller.loadCategories()
    }

    override fun showCategories(categories: List<CategoryModel>) {
        this.categories.value = categories
    }

    override fun showError(errorMessage: Int) {
        error.value = errorMessage
    }
}
