package com.example.swvl.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.swvl.App
import com.example.swvl.R
import com.example.swvl.utils.Utils.Companion.runWithCaution

/**
 * Adapter used to display each movie pics
 */
class MoviesPicsRecyclerViewAdapter(

    private val items: List<String>

) : RecyclerView.Adapter<MoviesPicsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_details_fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        runWithCaution({
            Glide.with(holder.itemView.context)
                .load(item)
                .placeholder(R.drawable.pic_loading)
                .into(holder.picIV)
        })
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val picIV: ImageView = view.findViewById(R.id.pic_iv)
    }
}