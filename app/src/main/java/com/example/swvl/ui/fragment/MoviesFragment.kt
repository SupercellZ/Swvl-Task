package com.example.swvl.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.swvl.R
import com.example.swvl.pojo.Movie
import com.example.swvl.ui.viewModel.MoviesViewModel
import com.example.swvl.utils.callbacks.OnTitleChange
import kotlinx.android.synthetic.main.movies_fragment.*

/**
 * A fragment representing a list of Items.
 */
class MoviesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    private lateinit var viewModel: MoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.movies_fragment, container, false)

        recyclerView = view.findViewById(R.id.recycler_view)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupViewModel()

        with(activity as OnTitleChange) {
            this.newTitle("Movies", false)
        }
    }


    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)

        //region loading
        viewModel.loading.observe(viewLifecycleOwner) {

            progressBar.visibility =
                if (it)
                    View.VISIBLE
                else
                    View.GONE
        }
        //endregion

        //region movies
        viewModel.movies.observe(viewLifecycleOwner) {

            //setup adapter
            setupAdapter(it)
        }

        if (viewModel.movies.value.isNullOrEmpty()) //if viewModel is loading for the first time.
            viewModel.loadMovies()
        //endregion
    }

    private fun setupAdapter(movies: List<Movie>) {
        recyclerView.adapter = MoviesRecyclerViewAdapter(
            this::movieOnClick,
            movies
        )
    }

    private fun movieOnClick(movie: Movie) {
        val navDirections =
            MoviesFragmentDirections.actionToMovieDetailsFragment(movie)
        view?.run {
            Navigation.findNavController(this).navigate(navDirections)
        }
    }

}