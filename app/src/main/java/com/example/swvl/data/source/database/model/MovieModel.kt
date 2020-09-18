package com.example.swvl.data.source.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.swvl.data.pojo.Movie


@Entity(tableName = "Movie")
data class MovieModel(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    val title: String,
    val year: Int,
    val cast: List<String>,
    val genres: List<String>,
    val rating: Int
) {
    fun toMovie() : Movie {
        return Movie(
            title = this.title,
            year = this.year,
            cast = this.cast,
            genres = this.genres,
            rating = this.rating
        )
    }
}