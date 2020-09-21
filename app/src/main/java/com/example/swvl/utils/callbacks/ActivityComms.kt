package com.example.swvl.utils.callbacks

import com.example.swvl.enums.FragType

interface ActivityComms {
    fun currentFrag(fragType: FragType, title: String = "")
    fun setupFilterCallback(filterMoviesCallback: (String) -> Unit)
}