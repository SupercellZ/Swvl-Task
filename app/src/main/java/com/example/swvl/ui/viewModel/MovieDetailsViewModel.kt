package com.example.swvl.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.swvl.base.BaseViewModel
import com.example.swvl.data.source.repo.MovieRepo
import kotlinx.coroutines.launch

class MovieDetailsViewModel : BaseViewModel() {

    //region movies LivewData
    val picUrls: LiveData<List<String>>
        get() = _picUrls
    private var _picUrls = MutableLiveData<List<String>>()
    //endregion

    fun loadPics(movieName: String) {
        viewModelScope.launch {

            try {
                showLoading()

                _picUrls.value = MovieRepo.loadPicUrls(movieName)

            } catch (e: Exception) {
                e.printStackTrace()
                loadingError()
            } finally {
                hideLoading()
            }
        }
    }

}