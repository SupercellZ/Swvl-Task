package com.example.swvl.pojo

import com.example.swvl.storage.model.MovieModel

data class Movie(
    val title: String,
    val year: Int,
    val cast: List<String>,
    val genres: List<String>,
    val rating: Int
) {
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