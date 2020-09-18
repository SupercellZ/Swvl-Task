package com.example.swvl.repo

import com.example.swvl.App
import com.example.swvl.pojo.Movie
import com.example.swvl.pojo.MovieResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepo {

    companion object {

        suspend fun loadMovies(): List<Movie> {
            val result: MutableList<Movie> = arrayListOf()

            withContext(Dispatchers.IO) {
                val database = App.app.getMyComponent().getAppDatabase()
                val movieDAO = database.getMovieDAO()
                var dbMovies = movieDAO.getAllMovies()

                if (dbMovies.isNullOrEmpty()) {

                    //region app is running for first time
                    //load from Json file
                    val moviesFromJson = getMoviesFromJson()

                    //map to DB Model
                    val movieModels = moviesFromJson.map { it.toMovieModel() }

                    //insert into DB
                    movieDAO.insert(movieModels)

                    dbMovies = movieModels
                    //endregion
                }

                result.addAll(dbMovies.map { it.toMovie() })
            }

            return result
        }

        private fun getMoviesFromJson(): List<Movie> {
            val movies: MutableList<Movie> = arrayListOf()
            try {
                val inputStream = App.app.applicationContext.assets.open("movies.json")
                val buffer = ByteArray(size = inputStream.available())
                inputStream.read(buffer)
                inputStream.close()

                val jsonRaw = String(buffer)

                val moshi = Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()

                val adapter: JsonAdapter<MovieResponse> =
                    moshi.adapter<MovieResponse>(MovieResponse::class.java)

                val response = adapter.fromJson(jsonRaw)
                response?.run {//safety, should always be true
                    movies.addAll(this.movies)
                }


            } catch (e: Exception) {
                e.printStackTrace()
            }

            return movies
        }

    }
}