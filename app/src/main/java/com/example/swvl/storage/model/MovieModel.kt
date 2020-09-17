package com.example.swvl.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Movie")
data class MovieModel(
    @PrimaryKey(autoGenerate = false) val id: Long,
    val title: String,
    val year: Int,
    val cast: List<String>,
    val genres: List<String>,
    val rating: Int
)