package com.example.swvl.ui.viewModel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.swvl.data.source.repo.MovieRepo
import com.example.swvl.ui.viewModel.MovieDetailsViewModel

/**
 * Factory class used to pass on MovieRepo as argument
 */
class MovieDetailsViewModelFactory(
    private val movieRepo: MovieRepo
) : ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailsViewModel(movieRepo) as T
    }

}