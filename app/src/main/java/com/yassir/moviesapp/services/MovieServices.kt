package com.yassir.moviesapp.services

import com.yassir.moviesapp.models.MovieDetailsResponse
import com.yassir.moviesapp.models.MovieListResponse
import com.yassir.moviesapp.utils.Constants.APIConstants.Companion.MOVIE_ID
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap


private const val GET_MOVIES_LIST =
    "discover/movie"
private const val GET_MOVIE_DETAILS =
    "movie/{movie_id}"

interface MovieServices {

    @GET(GET_MOVIES_LIST)
    suspend fun getMovieList(@QueryMap params: HashMap<String, String>): MovieListResponse

    @GET(GET_MOVIE_DETAILS)
    suspend fun getMovieDetail(
        @Path(MOVIE_ID) movieId: Int?,
        @QueryMap params: HashMap<String, String>
    ): MovieDetailsResponse
}