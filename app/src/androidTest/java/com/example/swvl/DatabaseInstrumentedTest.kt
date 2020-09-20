package com.example.swvl

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.swvl.data.source.database.AppDatabase
import com.example.swvl.data.source.database.dao.MovieDAO
import com.example.swvl.data.source.database.model.MovieModel
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DatabaseInstrumentedTest {

    private lateinit var movieDAO: MovieDAO
    private lateinit var db: AppDatabase

    @Before
            /**
             * Creates the components to be used, which are : DB & DAO objects
             */
    fun createDB() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        movieDAO = db.getMovieDAO()
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        db.close()
    }

    @Test
            /**
             * Tests DB operations by writing to & reading from the DB
             */
    fun dbOps() = runBlocking {
        val movieModel = MovieModel(
            title = "The Last Samurai",
            rating = 1,
            genres = arrayListOf("Action", "Drama"),
            cast = arrayListOf("Tom Cruise"),
            year = 2018
        )

        movieDAO.deleteAllMovies()
        movieDAO.insert(arrayListOf(movieModel))
        val allMovies = movieDAO.getAllMovies()
        assert(allMovies.size == 1 && allMovies[0] == movieModel)
    }
}