package com.yassir.moviesapp.repository

import com.yassir.moviesapp.models.MovieDetailsResponse
import com.yassir.moviesapp.models.MovieListResponse
import com.yassir.moviesapp.services.MovieServices
import com.yassir.moviesapp.services.request.MovieDetailsParams
import com.yassir.moviesapp.wrappers.Resource
import com.yassir.moviesapp.wrappers.callApi
import javax.inject.Inject

interface MovieRepository {
    suspend fun getMovieList(request: HashMap<String, String>): Resource<MovieListResponse>
    suspend fun getMovieDetails(
        request: MovieDetailsParams
    ): Resource<MovieDetailsResponse>

}

class DefaultMovieRepository @Inject constructor(private val movieServices: MovieServices) :
    MovieRepository {
    override suspend fun getMovieList(params: HashMap<String, String>): Resource<MovieListResponse> {
        return callApi {
            movieServices.getMovieList(
                params
            )
        }
    }

    override suspend fun getMovieDetails(
        request: MovieDetailsParams
    ): Resource<MovieDetailsResponse> {
        return callApi {
            movieServices.getMovieDetail(
                request.getPathField(), request.getParams()
            )
        }
    }
}