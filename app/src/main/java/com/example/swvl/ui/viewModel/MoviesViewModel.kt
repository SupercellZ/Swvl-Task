package com.example.swvl.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.swvl.data.pojo.Movie
import com.example.swvl.data.source.repo.MovieRepo
import com.example.swvl.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val movieRepo: MovieRepo
) : BaseViewModel() {

    //region movies LivewData
    val movies: LiveData<List<Movie>>
        get() = _movies
    private var _movies = MutableLiveData<List<Movie>>()
    //endregion

    fun loadMovies() {
        viewModelScope.launch {
            try {
                showLoading()

                _movies.value = movieRepo.loadMovies()

            } catch (e: Exception) {
                e.printStackTrace()
                loadingError()
            } finally {
                hideLoading()
            }
        }
    }
}