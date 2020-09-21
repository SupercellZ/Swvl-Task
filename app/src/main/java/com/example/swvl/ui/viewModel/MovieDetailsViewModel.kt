package com.example.swvl.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.swvl.data.source.repo.MovieRepo
import com.example.swvl.ui.base.BaseViewModel
import kotlinx.coroutines.launch

/**
 * ViewModel for handling Movie Details related ops
 */
class MovieDetailsViewModel(
    private val movieRepo: MovieRepo
) : BaseViewModel() {

    //region movies LivewData
    val picUrls: LiveData<List<String>>
        get() = _picUrls
    private var _picUrls = MutableLiveData<List<String>>()
    //endregion

    fun loadPics(movieName: String) {
        viewModelScope.launch {

            try {
                showLoading()

                _picUrls.value = movieRepo.loadPicUrls(movieName)

            } catch (e: Exception) {
                e.printStackTrace()
                loadingError()
            } finally {
                hideLoading()
            }
        }
    }

}