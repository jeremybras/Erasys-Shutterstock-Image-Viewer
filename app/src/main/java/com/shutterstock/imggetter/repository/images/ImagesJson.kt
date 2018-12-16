package com.shutterstock.imggetter.repository.images

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImagesRootJson(
    val page: Int,
    val per_page: Int,
    val total_count: Int,
    val data: List<ImageJson>
)

@JsonClass(generateAdapter = true)
data class ImageJson(
    val id: String,
    val assets: ImageAssetsJson
)

@JsonClass(generateAdapter = true)
data class ImageAssetsJson(
    val preview: AssetJson
)

@JsonClass(generateAdapter = true)
data class AssetJson(
    val height: Int,
    val url: String,
    val width: String
)
