package com.example.swvl.data.source.response.photoSize

import com.squareup.moshi.Json

data class PhotoSizeModel (
    @field:Json(name = "label") val label: String?,
    @field:Json(name = "source") val source: String?
)