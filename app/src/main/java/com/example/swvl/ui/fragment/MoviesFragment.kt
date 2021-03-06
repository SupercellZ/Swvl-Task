package com.example.swvl.ui.fragment

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import com.example.swvl.App
import com.example.swvl.R
import com.example.swvl.data.pojo.Movie
import com.example.swvl.ui.adapter.MoviesRecyclerViewAdapter
import com.example.swvl.ui.base.BaseFragment
import com.example.swvl.ui.viewModel.MoviesViewModel
import com.example.swvl.ui.viewModel.factory.MoviesViewModelFactory
import com.example.swvl.enums.FragType
import com.example.swvl.utils.Utils.Companion.runWithCaution
import kotlinx.android.synthetic.main.movies_fragment.*

/**
 * A fragment representing a list of Items.
 */
class MoviesFragment : BaseFragment() {

    private lateinit var viewModel: MoviesViewModel

    private lateinit var moviesAdapter: MoviesRecyclerViewAdapter

    private var rocketLaunched = false //used for initial Rocket animation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.movies_fragment, container, false)

        findRecyclerView(view)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        runWithCaution({

            setupRocket()

            currentFrag(FragType.Movies)

            setupRecyclerView(1)

            setupViewModel()

            setupFilterMoviesCallback {
                moviesAdapter.filter.filter(it)
            }
        })
    }

    /**
     * used for setting up Rocket animation
     */
    private fun setupRocket() {
        if (rocketLaunched)
            animation_view.visibility = View.GONE
        else {
            animation_view.postDelayed({
                val valueAnimator = ValueAnimator()

                valueAnimator.duration = 1_000

                val height = (animation_view.parent as ConstraintLayout).height
                valueAnimator.setIntValues(0, -height)
                valueAnimator.addUpdateListener {
                    animation_view.y = (valueAnimator.animatedValue as Int).toFloat()
                    if (valueAnimator.animatedValue == -height)
                        animation_view.visibility = View.GONE
                }
                valueAnimator.start()
            }, 2_000) //no need to wait for 2 secs coz loading is way faster than this, just thought it would look cool :D
            rocketLaunched = true
        }
    }


    private fun setupViewModel() {
        //region initialize
        val movieRepo = App.app.getMyComponent().getMovieRepo()
        viewModel = ViewModelProvider(
            this,
            MoviesViewModelFactory(
                movieRepo
            )
        ).get(MoviesViewModel::class.java)
        //endregion

        super.setupViewModel(viewModel, progressBar)

        //region movies related

        //display movies whenever they are updated
        viewModel.movies.observe(viewLifecycleOwner) {

            //setup adapter

            moviesAdapter = MoviesRecyclerViewAdapter(this::movieOnClick, it)
            setupAdapter(moviesAdapter)
        }

        if (viewModel.movies.value.isNullOrEmpty()) //if viewModel is loading for the first time.
            viewModel.loadMovies()
        //endregion
    }

    /**
     * called whenever a Movie item is clicked in the adapter, navigates to MovieDetails Fragment
     */
    private fun movieOnClick(movie: Movie) {
        val navDirections =
            MoviesFragmentDirections.actionToMovieDetailsFragment(movie)
        view?.run {
            Navigation.findNavController(this).navigate(navDirections)
        }
    }

}