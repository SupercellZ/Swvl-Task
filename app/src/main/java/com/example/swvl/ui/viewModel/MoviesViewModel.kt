package com.example.swvl.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swvl.data.pojo.Movie
import com.example.swvl.data.source.repo.MovieRepo
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {
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
}