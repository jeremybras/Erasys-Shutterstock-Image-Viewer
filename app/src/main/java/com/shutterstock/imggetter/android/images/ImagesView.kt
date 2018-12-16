package com.shutterstock.imggetter.android.images

import com.octo.workerdecorator.annotation.Decorate
import com.shutterstock.imggetter.core.images.Image

@Decorate(weak = true, mutable = true)
interface ImagesView {
    fun showImages(images: List<Image>)
}
