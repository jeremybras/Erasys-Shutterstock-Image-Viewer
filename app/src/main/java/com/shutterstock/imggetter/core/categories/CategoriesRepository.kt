package com.shutterstock.imggetter.core.categories

interface CategoriesRepository {
    fun loadCategories(): List<Category>?
}
