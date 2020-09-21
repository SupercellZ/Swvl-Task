package com.example.swvl.utils.callbacks

import com.example.swvl.enums.FragType

/**
 * Used for communication between Fragments & MainActivity
 */
interface ActivityComms {
    fun currentFrag(fragType: FragType, title: String = "")
    fun setupFilterCallback(filterMoviesCallback: (String) -> Unit)
}