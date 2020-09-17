package com.example.swvl.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.swvl.storage.model.MovieModel

@Dao
interface MovieDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieModels: List<MovieModel>)

    @Query("SELECT * FROM movie")
    suspend fun getAllMovies() : List<MovieModel>
}