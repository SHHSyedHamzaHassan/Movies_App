package com.yassir.moviesapp.di.modules

import com.yassir.moviesapp.repository.MovieRepository
import com.yassir.moviesapp.usecases.MovieDetailsUseCase
import com.yassir.moviesapp.usecases.MovieListUseCase
import com.yassir.moviesapp.utils.ResourceHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @ViewModelScoped
    @Provides
    fun provideMovieListUseCase(
        movieRepository: MovieRepository,
        resourceHandler: ResourceHandler
    ): MovieListUseCase {
        return MovieListUseCase(movieRepository, resourceHandler, Dispatchers.IO)
    }

    @ViewModelScoped
    @Provides
    fun provideMovieDetailsUseCase(
        movieRepository: MovieRepository,
        resourceHandler: ResourceHandler
    ): MovieDetailsUseCase {
        return MovieDetailsUseCase(movieRepository, resourceHandler, Dispatchers.IO)
    }
}