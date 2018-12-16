package com.shutterstock.imggetter.android.images

import com.octo.workerdecorator.annotation.Decorate
import com.shutterstock.imggetter.core.images.ImagesInteractor

@Decorate
interface ImagesController {
    fun loadImages(category: String)
    fun query(category: String, query: String)
}

class ImagesControllerImpl(
    private val interactor: ImagesInteractor
) : ImagesController {
    override fun query(category: String, query: String) {
        if (query.isNotEmpty()) {
            interactor.query(category, query)
        }
    }

    override fun loadImages(category: String) {
        interactor.loadImages(category)
    }
}
