package com.example.swvl.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swvl.R
import com.example.swvl.data.pojo.Movie
import com.example.swvl.ui.adapter.MoviesPicsRecyclerViewAdapter
import com.example.swvl.ui.viewModel.MovieDetailsViewModel
import com.example.swvl.utils.Utils.Companion.runWithCaution
import com.example.swvl.utils.callbacks.OnTitleChange
import kotlinx.android.synthetic.main.movie_details_fragment.*
import kotlinx.android.synthetic.main.movie_details_fragment.progressBar
import kotlinx.android.synthetic.main.movies_fragment.*

class MovieDetailsFragment : Fragment() {

    private lateinit var movie: Movie

    private lateinit var recyclerView: RecyclerView

    private lateinit var viewModel: MovieDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.movie_details_fragment, container, false)

        runWithCaution({
            recyclerView = view.findViewById(R.id.recycler_view)
        })

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        try {
            getArgs()

            setupToolbarTitle(movie)

            setupViewModel()

            displayMovieData()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getArgs() {
        movie = arguments?.get("movie") as Movie
    }

    private fun setupToolbarTitle(movie: Movie) {
        with(activity as OnTitleChange) {
            this.newTitle(movie.title, true)
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)

        viewModel.loading.observe(viewLifecycleOwner) {

            runWithCaution({
                progressBar.visibility =
                    if (it)
                        View.VISIBLE
                    else
                        View.GONE
            })
        }

        viewModel.picUrls.observe(viewLifecycleOwner) {

            //setup adapter
            runWithCaution({
                setupAdapter(it)
            })

        }
    }

    private fun setupAdapter(urls: List<String>) {
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter =
            MoviesPicsRecyclerViewAdapter(
                urls
            )
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