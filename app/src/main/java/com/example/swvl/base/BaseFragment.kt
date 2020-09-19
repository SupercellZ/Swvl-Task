package com.example.swvl.base

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swvl.R
import com.example.swvl.utils.Utils.Companion.runWithCaution
import com.example.swvl.utils.callbacks.OnTitleChange

abstract class BaseFragment : Fragment() {

    protected lateinit var recyclerView: RecyclerView
    private lateinit var baseViewModel: BaseViewModel


    fun setupToolbar(title: String, backVisible: Boolean) {
        with(activity as OnTitleChange) {
            this.newTitle(title, backVisible)
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