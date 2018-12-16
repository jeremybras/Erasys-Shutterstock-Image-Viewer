package com.shutterstock.imggetter.android.categories

import com.shutterstock.imggetter.R
import com.shutterstock.imggetter.core.categories.Category

class CategoriesPresenter(
    private val view: CategoriesView
) {
    fun presentCategories(categories: List<Category>) {
        view.showCategories(
            categories.map {
                CategoryModel(
                    id = it.id,
                    name = String.format("%s (%s)", it.name, it.id)
                )
            }
        )
    }

    fun presentNoCategories() {
        view.showError(R.string.repository_exception)
    }
}
