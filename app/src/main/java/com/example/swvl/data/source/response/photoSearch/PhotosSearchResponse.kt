package com.example.swvl.data.source.response.photoSearch

import com.squareup.moshi.Json

data class PhotosSearchResponse(
    @field:Json(name = "photos") val photosRoot: Photos?
)