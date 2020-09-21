package com.example.swvl.ui.activity

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.get
import com.example.swvl.R
import com.example.swvl.enums.FragType
import com.example.swvl.utils.callbacks.ActivityComms
import com.example.swvl.utils.hideKeyboard
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ActivityComms {

    private lateinit var currentFrag: FragType
    private lateinit var searchView: SearchView

    private lateinit var filterMoviesCallback: (String) -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
    }

    override fun currentFrag(fragType: FragType, title: String) {
        currentFrag = fragType
        invalidateOptionsMenu()

        supportActionBar?.run {
            this.title = title
            setDisplayHomeAsUpEnabled(fragType == FragType.MovieDetails)
        }
    }

    override fun setupFilterCallback(filterMoviesCallback: (String) -> Unit) {
        this.filterMoviesCallback = filterMoviesCallback
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)

        val searchManager: SearchManager? =
            getSystemService(Context.SEARCH_SERVICE) as? SearchManager
        val searchView: SearchView? = menu?.get(0)?.actionView as? SearchView

        if (searchManager != null && searchView != null) {
            this.searchView = searchView
            searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
            searchView.maxWidth = Int.MAX_VALUE

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    hideKeyboard()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.run(filterMoviesCallback)
                    return true
                }
            })
        }

        return currentFrag == FragType.Movies
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true;
            }
            R.id.wtf -> return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (closeSearchView().not())
            super.onBackPressed()
    }

    /**
     * Returns true if it was handled & close the SearchView, false otherwise
     */
    private fun closeSearchView(): Boolean {
        var result = false
        if (this::searchView.isInitialized && searchView.isIconified.not()) {
            searchView.isIconified = true
            result = true
        }
        return result
    }
}