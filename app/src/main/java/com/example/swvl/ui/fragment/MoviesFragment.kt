package com.example.swvl.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import com.example.swvl.R
import com.example.swvl.data.pojo.Movie
import com.example.swvl.ui.adapter.MoviesRecyclerViewAdapter
import com.example.swvl.ui.viewModel.MoviesViewModel
import com.example.swvl.utils.Utils.Companion.runWithCaution
import kotlinx.android.synthetic.main.movies_fragment.*

/**
 * A fragment representing a list of Items.
 */
//class MoviesFragment : Fragment() {
class MoviesFragment : BaseFragment() {

    private lateinit var viewModel: MoviesViewModel

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
            setupToolbar("Movies", false)

            setupRecyclerView(1)

            setupViewModel()
        })
    }


    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)

        super.setupViewModel(viewModel, progressBar)

        //region movies
        viewModel.movies.observe(viewLifecycleOwner) {

            //setup adapter
            setupAdapter(
                MoviesRecyclerViewAdapter(
                    this::movieOnClick,
                    it
                )
            )
        }

        if (viewModel.movies.value.isNullOrEmpty()) //if viewModel is loading for the first time.
            viewModel.loadMovies()
        //endregion
    }

    private fun movieOnClick(movie: Movie) {
        val navDirections =
            MoviesFragmentDirections.actionToMovieDetailsFragment(movie)
        view?.run {
            Navigation.findNavController(this).navigate(navDirections)
        }
    }

}