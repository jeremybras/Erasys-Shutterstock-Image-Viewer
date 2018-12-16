package com.shutterstock.imggetter.android.categories

import android.support.annotation.StringRes
import com.octo.workerdecorator.annotation.Decorate

@Decorate(weak = true, mutable = true)
interface CategoriesView {
    fun showCategories(categories: List<CategoryModel>)
    fun showError(@StringRes errorMessage: Int)
}
