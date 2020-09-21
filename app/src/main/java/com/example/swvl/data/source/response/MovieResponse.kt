package com.example.swvl.data.source.response

import com.example.swvl.data.pojo.Movie

/**
 * Used to handle movies being parsed from JSON built-in file
 */
data class MovieResponse(val movies: List<Movie>)