package com.yassir.moviesapp.usecases

import com.yassir.moviesapp.R
import com.yassir.moviesapp.models.MovieDetailsResponse
import com.yassir.moviesapp.repository.MovieRepository
import com.yassir.moviesapp.services.request.MovieDetailsParams
import com.yassir.moviesapp.utils.ResourceHandler
import com.yassir.moviesapp.wrappers.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieDetailsUseCase(
    private val movieRepository: MovieRepository,
    private val resourceHandler: ResourceHandler,
    dispatcher: CoroutineDispatcher
) : FlowUseCase<MovieDetailsParams, Resource<MovieDetailsResponse>>(dispatcher) {

    override fun execute(parameters: MovieDetailsParams): Flow<Resource<MovieDetailsResponse>> =
        flow {
            emit(Resource.Loading)
            when (val data = movieRepository.getMovieDetails(parameters)) {
                is Resource.Success -> {
                    emit(Resource.Success(data.data))
                }
                is Resource.Error -> {
                    emit(Resource.Error(data.exception))
                }
                else -> emit(Resource.Error(IllegalStateException(resourceHandler.getString(R.string.error_generic_api_error))))
            }
        }
}