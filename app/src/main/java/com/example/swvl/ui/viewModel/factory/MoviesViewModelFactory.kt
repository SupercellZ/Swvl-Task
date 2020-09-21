package com.example.swvl.ui.viewModel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.swvl.data.source.repo.MovieRepo
import com.example.swvl.ui.viewModel.MoviesViewModel

/**
 * Factory class used to pass on MovieRepo as argument
 */
class MoviesViewModelFactory(
    private val movieRepo: MovieRepo
) : ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MoviesViewModel(movieRepo) as T
    }

}