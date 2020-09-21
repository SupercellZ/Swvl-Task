package com.example.swvl.data.source.response.photoSize

import com.example.swvl.data.source.response.photoSearch.Photos
import com.squareup.moshi.Json

/**
 * Used to handle API Response (Root Response)
 */
data class PhotoSizeResponse(
    @field:Json(name = "sizes") val photoSizes: Sizes?
)