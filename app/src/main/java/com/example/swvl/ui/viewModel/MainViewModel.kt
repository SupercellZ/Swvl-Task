package com.example.swvl.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swvl.App
import com.example.swvl.pojo.Movie
import com.example.swvl.pojo.MovieResponse
import com.example.swvl.repo.MovieRepo
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    //region loading LiveData
    val loading: LiveData<Boolean>
        get() = _loading
    private var _loading = MutableLiveData<Boolean>(false)
    //endregion

    //region movies LivewData
    val movies: LiveData<List<Movie>>
        get() = _movies
    private var _movies = MutableLiveData<List<Movie>>()
    //endregion

    private fun showLoading() {
        _loading.value = true;
    }

    private fun hideLoading() {
        _loading.value = false;
    }

    fun loadMovies() {
        viewModelScope.launch {
            try {
                showLoading()

                _movies.value = MovieRepo.loadMovies()

            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                hideLoading()
            }
        }
    }

    private fun lol() {
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

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}