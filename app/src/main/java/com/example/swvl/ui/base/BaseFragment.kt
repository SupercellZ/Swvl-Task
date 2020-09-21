package com.example.swvl.ui.base

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swvl.R
import com.example.swvl.enums.FragType
import com.example.swvl.utils.Utils.Companion.runWithCaution
import com.example.swvl.utils.callbacks.ActivityComms

/**
 * Base class for Fragments containing common functionality and simplifying the main setup for each Fragment
 */
abstract class BaseFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var baseViewModel: BaseViewModel


    fun currentFrag(fragType: FragType, title: String = getString(R.string.movies)) {
        with(activity as ActivityComms) {
            currentFrag(fragType, title)
        }
    }

    fun setupFilterMoviesCallback(filterMoviesCallback: (String) -> Unit) {
        with(activity as ActivityComms) {
            setupFilterCallback(filterMoviesCallback)
        }
    }

    fun findRecyclerView(view: View) {
        runWithCaution({
            recyclerView = view.findViewById(R.id.recycler_view)
        })
    }

    fun setupViewModel(
        baseViewModel: BaseViewModel,
        progressBar: View
    ) {
        this.baseViewModel = baseViewModel

        //region loading
        baseViewModel.loading.observe(viewLifecycleOwner) {

            progressBar.visibility =
                if (it)
                    View.VISIBLE
                else
                    View.GONE
        }
        //endregion
    }

    fun setupRecyclerView(
        columnCount: Int
    ) {
        recyclerView.layoutManager =
            if (columnCount == 1)
                LinearLayoutManager(context)
            else
                GridLayoutManager(context, columnCount)
    }

    fun <T : RecyclerView.ViewHolder> setupAdapter(
        adapter: RecyclerView.Adapter<T>
    ) {
        recyclerView.adapter = adapter
    }

}