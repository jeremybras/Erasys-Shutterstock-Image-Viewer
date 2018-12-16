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
    private var query: String = ""

    val images: MutableLiveData<List<Image>> = MutableLiveData()
    val error: SingleLiveEvent<Int> = SingleLiveEvent()

    override fun showImages(images: List<Image>) {
        this.images.value = this.images.value?.plus(images) ?: images
    }

    fun setCategory(category: String) {
        this.category = category
    }

    fun loadImages(firstLoading: Boolean = false) {
        if (firstLoading) {
            this.images.value = emptyList()
        }
        query = ""
        controller.loadImages(category, query, firstLoading)
    }

    fun loadQuery() {
        controller.loadImages(category, query, false)
    }

    fun query(query: String) {
        this.query = query
        this.images.value = emptyList()
        controller.loadImages(category, query, true)
    }
}
