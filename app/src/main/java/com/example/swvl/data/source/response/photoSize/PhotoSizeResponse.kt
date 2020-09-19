package com.example.swvl.data.source.response.photoSize

import com.example.swvl.data.source.response.photoSearch.Photos
import com.squareup.moshi.Json

data class PhotoSizeResponse(
    @field:Json(name = "sizes") val photoSizes: Sizes?
)