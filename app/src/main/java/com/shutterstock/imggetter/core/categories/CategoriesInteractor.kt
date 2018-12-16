package com.shutterstock.imggetter.core.categories

import com.shutterstock.imggetter.android.categories.CategoriesPresenter
import com.shutterstock.imggetter.repository.RepositoryException

class CategoriesInteractor(
    private val presenter: CategoriesPresenter,
    private val repository: CategoriesRepository
) {
    fun loadCategories() {
        try {
            repository.loadCategories()?.let {
                presenter.presentCategories(it.sortedBy(Category::name))
            } ?: presenter.presentNoCategories()
        } catch (exception: RepositoryException) {
            presenter.presentNoCategories()
        }
    }
}
