package com.shutterstock.imggetter.core.images

import com.shutterstock.imggetter.android.images.ImagesPresenter
import com.shutterstock.imggetter.repository.RepositoryException


class ImagesInteractor(
    private val presenter: ImagesPresenter,
    private val repository: ImagesRepository
) {
    fun loadImages(category: String, query: String, firstLoading: Boolean) {
        try {
            repository.loadImages(category, query, firstLoading)?.let {
                presenter.presentImages(it)
            } ?: presenter.presentNoImageFound()
        } catch (exception: RepositoryException) {

        }
    }

//    fun query(category: String, query: String, firstLoading: Boolean) {
//        try {
//            repository.query(category, query, firstLoading)?.let {
//                presenter.presentImages(it)
//            } ?: presenter.presentNoImageFound()
//        } catch (exception: RepositoryException) {
//
//        }
//    }
}