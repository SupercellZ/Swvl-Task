package com.example.swvl.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import com.example.swvl.R
import com.example.swvl.pojo.Movie
import com.example.swvl.ui.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()

        supportActionBar?.run {
//            title = "Fuck off xD"
        }

        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)

//        navController.navigate(R.id.action_to_movieDetailsFragment)
    }

    private fun setupViewModel() {

    }

    private fun displayMovies(movies: List<Movie>) {

    }
}