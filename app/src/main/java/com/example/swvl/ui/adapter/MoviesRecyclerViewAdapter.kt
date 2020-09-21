package com.example.swvl.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.swvl.R
import com.example.swvl.data.pojo.Movie

class MoviesRecyclerViewAdapter(

    private val onClick: Function1<Movie, Unit>,
    private val items: List<Movie>,
    private var filteredItems: List<Movie> = items
) : RecyclerView.Adapter<MoviesRecyclerViewAdapter.ViewHolder>(), Filterable {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = filteredItems[position]
        holder.yearTV.text = item.year.toString()
        holder.ratingTV.text = item.rating.toString()
        holder.contentView.text = item.title

        holder.itemView.setOnClickListener {
            onClick(item)
        }
    }

    override fun getItemCount(): Int = filteredItems.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val yearTV: TextView = view.findViewById(R.id.item_year)
        val ratingTV: TextView = view.findViewById(R.id.item_rating)
        val contentView: TextView = view.findViewById(R.id.content)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    override fun getFilter(): Filter {
        val filter = object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {

                //do logic here

                filteredItems =
                    if (constraint.isNullOrEmpty())
                        items
                    else {
                        val maxMoviesByYear = 5
                        val result: MutableList<Movie> = arrayListOf()

                        Log.e("MoviesAdapter", "Filtering now ..")

                        //filter by movie name :)
                        val filter =
                            items.filter { movie -> movie.title.contains(constraint, true) }

                        //hold 5 top rated movies for each year
                        val map =
                            HashMap<Int, MutableList<Movie>>() //year to collection of movies for that year (restricted to 5 items)

                        //region collect top 5 movies per year
                        for (movie in filter) {
                            val list = map[movie.year] ?: arrayListOf()

                            if (list.isEmpty()) //first element to be added
                                list.add(movie)
                            else {
                                if (list.size == maxMoviesByYear) { //list is full

                                    if (movie.rating > list.last().rating) { //replace last element only if current movie has better rating

                                        //region determine new element insertion index
                                        val startIndex = list.size - 2 //start from 2nd element from bottom since we already evaluated last one.
                                        for (i in startIndex downTo 0 step 1) {
                                            if (movie.rating > list[i].rating)
                                                continue
                                            list[i+1] = movie
                                            break
                                        }
                                        //endregion
                                    } //no else, not among top 5

                                } else { //list size didn't reach max threshold yet

                                    list.add(movie)
                                    list.sortByDescending { input -> input.rating }
                                }
                            }

                            map[movie.year] = list
                        }
                        //endregion

                        for (entry in map.toSortedMap().entries)
                            result.addAll(entry.value)

                        result
                    }
                Log.e("MoviesAdapter", "Finished filtering")


                val filterResults = FilterResults()
                filterResults.values = filteredItems
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                results?.run {
                    val values = results.values as? List<Movie>
                    if (values != null) {
                        filteredItems = values
                        notifyDataSetChanged()
                    }
                }
            }
        }

        return filter
    }
}