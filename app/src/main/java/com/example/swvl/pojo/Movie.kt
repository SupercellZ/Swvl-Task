package com.example.swvl.pojo

import com.example.swvl.storage.database.model.MovieModel
import java.io.Serializable

data class Movie(
    val title: String,
    val year: Int,
    val cast: List<String>,
    val genres: List<String>,
    val rating: Int
) : Serializable {
    fun toMovieModel() : MovieModel {
        return MovieModel(
            title = this.title,
            year = this.year,
            cast = this.cast,
            genres = this.genres,
            rating = this.rating
        )
    }
}