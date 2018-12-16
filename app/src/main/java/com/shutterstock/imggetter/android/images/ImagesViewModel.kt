package com.shutterstock.imggetter.android.images

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.shutterstock.imggetter.android.SingleLiveEvent
import com.shutterstock.imggetter.core.images.Image

class ImagesViewModel(
    private val controller: ImagesController
) : ViewModel(), ImagesView, LifecycleObserver {

    private lateinit var category: String
    val images: MutableLiveData<List<Image>> = MutableLiveData()
    val error: SingleLiveEvent<Int> = SingleLiveEvent()

    fun setCategory(category: String) {
        this.category = category
    }

    fun loadImages() {
        controller.loadImages(category)
    }

    override fun showImages(images: List<Image>) {
        this.images.value = images
    }

    fun query(query: String) {
        controller.query(category, query)
    }
}
