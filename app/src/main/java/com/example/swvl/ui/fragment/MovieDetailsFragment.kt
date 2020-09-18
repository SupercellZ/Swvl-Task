package com.example.swvl.ui.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.swvl.R
import com.example.swvl.pojo.Movie
import com.example.swvl.ui.viewModel.MainViewModel
import com.example.swvl.utils.callbacks.OnTitleChange
import kotlinx.android.synthetic.main.movie_details_fragment.*

class MovieDetailsFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val movie = arguments?.get("movie") as Movie

        with(activity as OnTitleChange) {
            this.newTitle(movie.title, true)
        }

        rating_tv.text = "${movie.rating.toFloat()}"
        materialRatingBar.rating = movie.rating.toFloat()
        year_tv.text = "${movie.year}"
        genres_tv.text = movie.genres.joinToString(separator = " - ")
        cast_tv.text = movie.cast.joinToString(separator = "\n")
    }

}