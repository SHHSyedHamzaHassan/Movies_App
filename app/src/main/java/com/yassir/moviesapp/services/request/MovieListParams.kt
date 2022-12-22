package com.yassir.moviesapp.services.request

import com.yassir.moviesapp.utils.Constants.APIConstants.Companion.API_KEY

class MovieListParams(apiKey: String) {
    var apiKey = apiKey

    var requestPrams = HashMap<String, String>()
    fun getParams(): HashMap<String, String> {
        requestPrams[API_KEY] = apiKey
        return requestPrams
    }
}