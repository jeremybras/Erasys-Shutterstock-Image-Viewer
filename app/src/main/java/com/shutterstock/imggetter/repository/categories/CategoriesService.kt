package com.shutterstock.imggetter.repository.categories

import retrofit2.Call
import retrofit2.http.GET

interface CategoriesService {
    @GET("images/categories")
    fun getCategories(): Call<CategoriesDataJson>
}
