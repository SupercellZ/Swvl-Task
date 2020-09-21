package com.example.swvl.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.swvl.App
import com.example.swvl.R
import com.example.swvl.data.pojo.Movie
import com.example.swvl.ui.adapter.MoviesPicsRecyclerViewAdapter
import com.example.swvl.ui.base.BaseFragment
import com.example.swvl.ui.viewModel.MovieDetailsViewModel
import com.example.swvl.ui.viewModel.factory.MovieDetailsViewModelFactory
import com.example.swvl.enums.FragType
import com.example.swvl.utils.Utils.Companion.runWithCaution
import kotlinx.android.synthetic.main.movie_details_fragment.*

/**
 * Fragment used to display a single Movie details
 */
class MovieDetailsFragment : BaseFragment() {

    private lateinit var movie: Movie //passed on as argument

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

            currentFrag(FragType.MovieDetails, movie.title)

            setupRecyclerView(2)

            setupViewModel()

            displayMovieData()
        })
    }

    private fun getArgs() {
        movie = MovieDetailsFragmentArgs.fromBundle(requireArguments()).movie
    }

    private fun setupViewModel() {
        //region initialize
        val movieRepo = App.app.getMyComponent().getMovieRepo()
        viewModel = ViewModelProvider(
            this,
            MovieDetailsViewModelFactory(
                movieRepo
            )
        ).get(MovieDetailsViewModel::class.java)
        //endregion

        super.setupViewModel(viewModel, animation_view)

        //display pics whenever they are updated
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