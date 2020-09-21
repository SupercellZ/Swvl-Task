package com.example.swvl.data.source.response.photoSearch

import com.squareup.moshi.Json

/**
 * Used to handle API Response
 */
data class Photos(
    @field:Json(name = "photo") val photos: List<PhotoSearchModel>?
)