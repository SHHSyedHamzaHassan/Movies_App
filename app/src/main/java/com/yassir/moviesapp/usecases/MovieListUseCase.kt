package com.yassir.moviesapp.usecases

import com.yassir.moviesapp.R
import com.yassir.moviesapp.models.MovieListResponse
import com.yassir.moviesapp.repository.MovieRepository
import com.yassir.moviesapp.utils.ResourceHandler
import com.yassir.moviesapp.wrappers.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class MovieListUseCase(
    private val movieRepository: MovieRepository,
    private val resourceHandler: ResourceHandler,
    dispatcher: CoroutineDispatcher
) : FlowUseCase<HashMap<String, String>, Resource<MovieListResponse>>(dispatcher) {

    override fun execute(parameters: HashMap<String, String>): Flow<Resource<MovieListResponse>> =
        flow {
            emit(Resource.Loading)
            when (val data = movieRepository.getMovieList(parameters)) {
                is Resource.Success -> {
                    emit(Resource.Success(data.data))
                }
                else -> emit(Resource.Error(IllegalStateException(resourceHandler.getString(R.string.error_generic_api_error))))
            }
        }
}