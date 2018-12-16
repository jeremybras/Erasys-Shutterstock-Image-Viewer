package com.shutterstock.imggetter.repository.images

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ImagesService {
    @GET("images/search")
    fun getImages(
        @Query("category") category: String,
        @Query("query") query: String = "",
        @Query("per_page") perPage: Int = 200
    ): Call<ImagesRootJson>
}
