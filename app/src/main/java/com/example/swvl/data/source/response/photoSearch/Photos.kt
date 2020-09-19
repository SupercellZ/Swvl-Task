package com.example.swvl.data.source.response.photoSearch

import com.squareup.moshi.Json

data class Photos(
    @field:Json(name = "photo") val photos: List<PhotoSearchModel>?
)