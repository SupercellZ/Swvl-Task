package com.example.swvl.data.source.repo

import com.example.swvl.App
import com.example.swvl.data.pojo.Movie
import com.example.swvl.data.source.database.LocalDataSource
import com.example.swvl.data.source.network.MovieService
import com.example.swvl.data.source.network.RemoteDataSource
import com.example.swvl.data.source.response.MovieResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepo(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun loadMovies(): List<Movie> {
        val result: MutableList<Movie> = arrayListOf()

        withContext(Dispatchers.IO) {
            val database = localDataSource.database
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
                .build()

            val adapter: JsonAdapter<MovieResponse> =
                moshi.adapter<MovieResponse>(
                    MovieResponse::class.java
                )

            val response = adapter.fromJson(jsonRaw)
            response?.run {//safety, should always be true
                movies.addAll(this.movies)
            }


        } catch (e: Exception) {
            e.printStackTrace()
        }

        return movies
    }


    suspend fun loadPicUrls(movieName: String): MutableList<String> {

        val result: MutableList<String> = arrayListOf()

        val movieService = remoteDataSource.movieService
        val search = movieService.search(text = movieName)

        search.photosRoot?.photos?.forEach {

            val url = it.id?.run {
                //catching in order to skip whoever fails and proceed to the next photo
                try {
                    val sizes = movieService.getSizes(photoId = this)
                    sizes.photoSizes?.getUrl()
                } catch (e: Exception) {
                    e.printStackTrace()
                    ""
                }
            }
            url?.run {
                result.add(url)
            }
        }

        return result
    }
}