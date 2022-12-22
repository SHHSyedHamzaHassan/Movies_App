package com.yassir.moviesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yassir.moviesapp.models.MovieDetailsResponse
import com.yassir.moviesapp.services.request.MovieDetailsParams
import com.yassir.moviesapp.ui.base.BaseViewModel
import com.yassir.moviesapp.usecases.MovieDetailsUseCase
import com.yassir.moviesapp.utils.Constants
import com.yassir.moviesapp.utils.Constants.ApplicationConstants.Companion.EMPTY_STRING
import com.yassir.moviesapp.wrappers.Event
import com.yassir.moviesapp.wrappers.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieDetailsUseCase: MovieDetailsUseCase,
) : BaseViewModel() {


    //#region Instance Variables
    private var movieId: Int = 0
    private lateinit var movieDetailsResponse: MovieDetailsResponse
    var errorMessage: String = EMPTY_STRING
    private val _shareData =
        MutableLiveData<Event<APIDataShareToFragment>>()
    val shareData: LiveData<Event<APIDataShareToFragment>> =
        _shareData

    //#endregion Instance Variables

    //#region API Methods
    private fun getMovieDetails() {
        viewModelScope.launch {
            movieDetailsUseCase.invoke(getMovieDetailsParams()).collect {
                when (it) {
                    Resource.Loading -> setLoading(loading = true)
                    is Resource.Success -> {
                        setLoading(loading = false)
                        movieDetailsResponse = it.data
                        _shareData.postValue(Event(APIDataShareToFragment.MOVIE_DETAILS))
                    }
                    else -> {
                        setLoading(loading = false)
                        errorMessage = (it as Resource.Error).exception.message!!
                        _shareData.postValue(Event(APIDataShareToFragment.ERROR))
                    }
                }
            }
        }
    }
    //#endregion API Methods

    //region Data Methods
    fun getMovieDetailsResposne(): MovieDetailsResponse {
        return movieDetailsResponse
    }

    fun getMovieId(movieId: Int) {
        this.movieId = movieId
        getMovieDetails()
    }
    //#endregion Data Methods

    //#region Private Methods
    private fun getMovieDetailsParams(): MovieDetailsParams {
        return MovieDetailsParams(
            Constants.DataProviderEndPoints.API_KEY_VALUE,
            movieId,
        )
    }
    //#endregion Private Methods

    //#region Enumeration
    enum class APIDataShareToFragment {
        MOVIE_DETAILS, ERROR
    }
    //#endregion Enumeration
}
