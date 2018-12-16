package com.shutterstock.imggetter.repository.categories

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoriesDataJson(
    val data: List<CategoriesJson>
)

@JsonClass(generateAdapter = true)
data class CategoriesJson(
    val id: String,
    val name: String
)
