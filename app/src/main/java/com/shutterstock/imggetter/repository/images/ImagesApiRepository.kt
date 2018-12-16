package com.shutterstock.imggetter.repository.images

import com.shutterstock.imggetter.core.images.Image
import com.shutterstock.imggetter.core.images.ImagesRepository
import com.shutterstock.imggetter.repository.RepositoryException
import java.io.IOException

class ImagesApiRepository(
    private val service: ImagesService
) : ImagesRepository {
    override fun loadImages(category: String): List<Image> {
        try {
            service.getImages(category).execute().body()?.let {
                return transformToEntity(it.data)
            } ?: throw RepositoryException()
        } catch (io: IOException) {
            throw RepositoryException(cause = io)
        }
    }

    override fun query(category: String, query: String): List<Image>? {
        try {
            service.getImages(category, query).execute().body()?.let {
                return transformToEntity(it.data)
            } ?: throw RepositoryException()
        } catch (io: IOException) {
            throw RepositoryException(cause = io)
        }
    }

    private fun transformToEntity(data: List<ImageJson>): List<Image> {
        return data.map {
            Image(
                id = it.id,
                url = it.assets.preview.url,
                width = it.assets.preview.width,
                height = it.assets.preview.height
            )
        }
    }
}
