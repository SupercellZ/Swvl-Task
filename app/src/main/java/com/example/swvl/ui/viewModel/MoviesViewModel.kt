package com.example.swvl.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.swvl.base.BaseViewModel
import com.example.swvl.data.pojo.Movie
import com.example.swvl.data.source.repo.MovieRepo
import kotlinx.coroutines.launch

class MoviesViewModel : BaseViewModel() {

    //region movies LivewData
    val movies: LiveData<List<Movie>>
        get() = _movies
    private var _movies = MutableLiveData<List<Movie>>()
    //endregion

    fun loadMovies() {
        viewModelScope.launch {
            try {
                showLoading()

                _movies.value = MovieRepo.loadMovies()

            } catch (e: Exception) {
                e.printStackTrace()
                loadingError()
            } finally {
                hideLoading()
            }
        }
    }
}