package com.shutterstock.imggetter.core.images

import com.shutterstock.imggetter.android.images.ImagesPresenter
import com.shutterstock.imggetter.repository.RepositoryException


class ImagesInteractor(
    private val presenter: ImagesPresenter,
    private val repository: ImagesRepository
) {
    fun loadImages(category: String) {
        try {
            repository.loadImages(category)?.let {
                presenter.presentImages(it)
            } ?: presenter.presentNoImageFound()
        } catch (exception: RepositoryException) {

        }
    }

    fun query(category: String, query: String) {
        try {
            repository.query(category, query)?.let {
                presenter.presentImages(it)
            } ?: presenter.presentNoImageFound()
        } catch (exception: RepositoryException) {

        }
    }
}