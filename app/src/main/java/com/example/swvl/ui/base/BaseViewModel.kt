package com.example.swvl.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Base class for ViewModel containing common functionality and simplifying the main setup for each ViewModel
 */
abstract class BaseViewModel : ViewModel() {

    //region loading LiveData
    val loading: LiveData<Boolean>
        get() = _loading
    private var _loading = MutableLiveData<Boolean>(false)
    //endregion

    //region error LiveData
    val errorLoading: LiveData<Boolean>
        get() = _errorLoading
    private var _errorLoading = MutableLiveData<Boolean>(false)
    //endregion

    //region loading methods
    protected fun showLoading() {
        _loading.value = true
    }

    protected fun hideLoading() {
        _loading.value = false
    }

    protected fun loadingError() {
        _errorLoading.value = true
        _errorLoading.value = false
    }
    //endregion
}