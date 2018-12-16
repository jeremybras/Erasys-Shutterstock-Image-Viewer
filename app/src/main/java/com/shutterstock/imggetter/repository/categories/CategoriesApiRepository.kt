package com.shutterstock.imggetter.repository.categories

import com.shutterstock.imggetter.core.categories.CategoriesRepository
import com.shutterstock.imggetter.core.categories.Category
import com.shutterstock.imggetter.repository.RepositoryException
import java.io.IOException

class CategoriesApiRepository(
    private val service: CategoriesService
) : CategoriesRepository {
    override fun loadCategories(): List<Category>? {
        try {
            service.getCategories().execute().body()?.let {
                return transformToEntity(it.data)
            } ?: throw RepositoryException()
        } catch (io: IOException) {
            throw RepositoryException(cause = io)
        }
    }

    private fun transformToEntity(body: List<CategoriesJson>): List<Category>? {
        return body.map {
            Category(it.id, it.name)
        }
    }
}
