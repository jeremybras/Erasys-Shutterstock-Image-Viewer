package com.shutterstock.imggetter.android.images

import com.shutterstock.imggetter.core.images.Image

class ImagesPresenter(
    private val view: ImagesView
) {
    fun presentImages(images: List<Image>) {
        view.showImages(images)
    }

    fun presentNoImageFound() {

    }
}
