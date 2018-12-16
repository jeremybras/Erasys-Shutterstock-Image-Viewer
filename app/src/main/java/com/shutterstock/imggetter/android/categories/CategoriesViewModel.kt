package com.shutterstock.imggetter.android.categories

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
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
