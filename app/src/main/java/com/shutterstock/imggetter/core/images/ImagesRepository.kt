package com.shutterstock.imggetter.core.images

interface ImagesRepository {
    fun loadImages(category: String, query: String, firstLoading: Boolean): List<Image>?
//    fun query(category: String, query: String, firstLoading: Boolean): List<Image>?
}
