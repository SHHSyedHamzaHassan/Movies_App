package com.yassir.moviesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yassir.moviesapp.models.MovieListResponse
import com.yassir.moviesapp.services.request.MovieListParams
import com.yassir.moviesapp.ui.base.BaseViewModel
import com.yassir.moviesapp.usecases.MovieListUseCase
import com.yassir.moviesapp.utils.Constants.DataProviderEndPoints.Companion.API_KEY_VALUE
import com.yassir.moviesapp.wrappers.Event
import com.yassir.moviesapp.wrappers.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieListUseCase: MovieListUseCase,
) : BaseViewModel() {


    //#region Instance Variables
    private lateinit var movieListResponse : MovieListResponse
    private val _shareData =
        MutableLiveData<Event<APIDataShareToFragment>>()
    val shareData: LiveData<Event<APIDataShareToFragment>> =
        _shareData
    private val _navigationAction =
        MutableLiveData<Event<Navigation>>()
    val navigationAction: LiveData<Event<Navigation>> =
        _navigationAction

    //#endregion Instance Variables

    init {
        getMovieList()
    }

    //#region API Methods
    private fun getMovieList() {
        viewModelScope.launch {
            movieListUseCase.invoke(getMovieRequestParams()).collect {
                when (it) {
                    Resource.Loading -> setLoading(loading = true)
                    is Resource.Success -> {
                        setLoading(loading = false)
                        movieListResponse = it.data
                        _shareData.postValue(Event(APIDataShareToFragment.MOVIE_LIST))
                    }
                    else -> {
                        setLoading(loading = false)
                    }
                }
            }
        }
    }
    //#endregion API Methods

    //region Data Methods
    fun getMovieListData(): MovieListResponse {
        return movieListResponse
    }
    //#endregion Data Methods

    //#region Private Methods
    private fun getMovieRequestParams(): HashMap<String, String> {
        return MovieListParams(
            API_KEY_VALUE
        ).getParams()
    }
    //#endregion Private Methods

    //#region Enumeration
    enum class APIDataShareToFragment {
        MOVIE_LIST
    }
    enum class Navigation {
        DISMISS
    }
    //#endregion Enumeration
}