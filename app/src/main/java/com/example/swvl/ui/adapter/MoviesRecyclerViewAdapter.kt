package com.example.swvl.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.swvl.R
import com.example.swvl.data.pojo.Movie

class MoviesRecyclerViewAdapter(

    private val onClick: Function1<Movie, Unit>,
    private val items: List<Movie>

) : RecyclerView.Adapter<MoviesRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.idView.text = item.rating.toString()
        holder.contentView.text = item.title
        holder.itemView.setOnClickListener {
            onClick(item)
        }
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.item_number)
        val contentView: TextView = view.findViewById(R.id.content)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}