package com.shutterstock.imggetter.android.images

import com.octo.workerdecorator.annotation.Decorate
import com.shutterstock.imggetter.core.images.ImagesInteractor

@Decorate
interface ImagesController {
    fun loadImages(category: String, query: String, firstLoading: Boolean)
//    fun query(category: String, query: String, firstLoading: Boolean)
}

class ImagesControllerImpl(
    private val interactor: ImagesInteractor
) : ImagesController {
    override fun loadImages(category: String, query: String, firstLoading: Boolean) {
        interactor.loadImages(category, query, firstLoading)
    }
}
