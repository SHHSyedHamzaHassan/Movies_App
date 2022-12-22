package com.yassir.moviesapp.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yassir.moviesapp.wrappers.Event
import com.yassir.moviesapp.wrappers.Resource

open class BaseViewModel(): ViewModel() {


    // Mutable/LiveData of String resource reference Event
    private val _toastMessage = MutableLiveData<Event<String>>()
    val toastMessage: LiveData<Event<String>>
        get() = _toastMessage

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MutableLiveData<Event<Boolean>>()
    val error: LiveData<Event<Boolean>>
        get() = _error

    private val _closeAction = MutableLiveData<Event<Unit>>()
    val closeAction: LiveData<Event<Unit>>
        get() = _closeAction

    private val _finishAction = MutableLiveData<Event<Unit>>()
    val finishAction: LiveData<Event<Unit>>
        get() = _finishAction

    private val _emptyResult = MutableLiveData<Event<Unit>>()
    val emptyResult: LiveData<Event<Unit>>
        get() = _emptyResult

    fun setLoading(loading: Boolean) {
        _loading.value = loading
    }

    fun setToastMessage(message: String?) {
        message?.let {
            _toastMessage.value = Event(message)
        }
    }

    fun setError(error: Boolean) {
        _error.value = Event(error)
    }

    fun setCloseAction() {
        _closeAction.value = Event(Unit)
    }

    fun setFinishAction() {
        _finishAction.value = Event(Unit)
    }

    protected fun handleResourceResponse(resource: Resource<*>, success: ((data: Any?) -> Unit)? = null) {
        when (resource) {
            is Resource.Success -> {
                setLoading(false)
                success?.invoke(resource.data)
                setCloseAction()
            }

            is Resource.Loading -> {
                setLoading(true)
            }

            is Resource.Empty -> {
                setLoading(false)
                _emptyResult.value = Event(Unit)
            }

            is Resource.Error -> {
                setLoading(false)
                setToastMessage(resource.exception.message)
                setError(true)
            }
        }
    }
}