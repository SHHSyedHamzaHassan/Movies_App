package com.yassir.moviesapp.services.request

import com.yassir.moviesapp.utils.Constants

class MovieDetailsParams(apiKey: String, movieId: Int) {
    private var apiKey = apiKey
    private var movieId = movieId

    var requestParams = HashMap<String, String>()
    fun getParams(): HashMap<String, String> {
        requestParams[Constants.APIConstants.API_KEY] = apiKey
        return requestParams
    }

    fun getPathField(): Int {
        return movieId
    }
}