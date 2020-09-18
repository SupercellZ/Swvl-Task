package com.example.swvl.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.swvl.R
import com.example.swvl.pojo.Movie
import com.example.swvl.ui.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.movies.observe(this) {
            //do stuff :)
            displayMovies(it)
        }

        if (viewModel.movies.value.isNullOrEmpty()) //I already know beforehand that there must be data; cannot be empty by design.
            viewModel.loadMovies()
    }

    private fun displayMovies(movies: List<Movie>) {

    }
}