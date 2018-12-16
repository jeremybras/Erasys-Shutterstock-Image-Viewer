package com.shutterstock.imggetter.core.images

interface ImagesRepository {
    fun loadImages(category: String): List<Image>?
    fun query(category: String, query: String): List<Image>?
}
