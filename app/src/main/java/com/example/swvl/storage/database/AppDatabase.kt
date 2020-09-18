package com.example.swvl.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.swvl.storage.database.dao.MovieDAO
import com.example.swvl.storage.database.model.MovieModel

@Database(
    entities = [
        MovieModel::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getMovieDAO(): MovieDAO
}