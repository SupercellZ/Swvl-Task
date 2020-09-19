package com.example.swvl.data.source.response.photoSize

import com.squareup.moshi.Json

data class Sizes(
    @field:Json(name = "size") val sizes: List<PhotoSizeModel>?
) {
    fun getUrl() : String {
//        val photoSizeModel = sizes?.find { model -> model.label == "Medium 800" }
        val photoSizeModel = sizes?.find { model -> model.label == "Large Square" }
        return photoSizeModel?.source ?: ""
    }
}