package com.example.swvl.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swvl.R
import com.example.swvl.data.pojo.Movie
import com.example.swvl.ui.adapter.MoviesPicsRecyclerViewAdapter
import com.example.swvl.ui.viewModel.MovieDetailsViewModel
import com.example.swvl.utils.Utils.Companion.runWithCaution
import kotlinx.android.synthetic.main.movie_details_fragment.*

class MovieDetailsFragment : BaseFragment() {

    private lateinit var movie: Movie

    private lateinit var viewModel: MovieDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.movie_details_fragment, container, false)

        findRecyclerView(view)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        runWithCaution({
            getArgs()

            setupToolbar(movie.title, true)

            setupRecyclerView(2)

            setupViewModel()

            displayMovieData()
        })
    }

    private fun getArgs() {
        movie = arguments?.get("movie") as Movie
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)

        super.setupViewModel(viewModel, progressBar)

        viewModel.picUrls.observe(viewLifecycleOwner) {

            //setup adapter
            runWithCaution({
                setupAdapter(
                    MoviesPicsRecyclerViewAdapter(it)
                )
            })

        }
    }

    private fun displayMovieData() {
        rating_tv.text = "${movie.rating.toFloat()}"
        materialRatingBar.rating = movie.rating.toFloat()
        year_tv.text = "${movie.year}"
        genres_tv.text = movie.genres.joinToString(separator = " - ")
        cast_tv.text = movie.cast.joinToString(separator = "\n")

        viewModel.loadPics(movie.title)
    }

}