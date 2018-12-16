package com.shutterstock.imggetter.android.categories

import com.octo.workerdecorator.annotation.Decorate
import com.shutterstock.imggetter.core.categories.CategoriesInteractor

@Decorate
interface CategoriesController {
    fun loadCategories()
}

class CategoriesControllerImpl(
    private val interactor: CategoriesInteractor
) : CategoriesController {
    override fun loadCategories() {
        interactor.loadCategories()
    }
}
