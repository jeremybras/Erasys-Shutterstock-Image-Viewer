package com.shutterstock.imggetter.repository.images

import com.shutterstock.imggetter.core.images.Image
import com.shutterstock.imggetter.core.images.ImagesRepository
import com.shutterstock.imggetter.repository.RepositoryException
import timber.log.Timber
import java.io.IOException

class ImagesApiRepository(
    private val service: ImagesService
) : ImagesRepository {

    companion object {
        private const val INIT_PAGE = 1
        private const val LOAD_SIZE = 15
    }

    private var page = INIT_PAGE

    override fun loadImages(category: String, query: String, firstLoading: Boolean): List<Image> {
        try {
            if (firstLoading) {
                page = INIT_PAGE
            }
            Timber.d("Loading $LOAD_SIZE images at page $page")
            service.getImages(
                category = category,
                page = page,
                perPage = LOAD_SIZE,
                query = query
            ).execute().body()?.let {
                page++
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
