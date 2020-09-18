package com.example.swvl.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.swvl.R
import com.example.swvl.data.pojo.Movie
import com.example.swvl.ui.viewModel.MainViewModel
import com.example.swvl.utils.callbacks.OnTitleChange

class MainActivity : AppCompatActivity() , OnTitleChange {

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
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private fun displayMovies(movies: List<Movie>) {

    }

    override fun newTitle(text: String, backVisible: Boolean) {
        supportActionBar?.run {
            title = text
            setDisplayHomeAsUpEnabled(backVisible)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true;
            }
        }
        return super.onOptionsItemSelected(item)
    }
}