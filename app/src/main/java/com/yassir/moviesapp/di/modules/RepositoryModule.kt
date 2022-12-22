package com.yassir.moviesapp.di.modules

import com.yassir.moviesapp.repository.DefaultMovieRepository
import com.yassir.moviesapp.repository.MovieRepository
import com.yassir.moviesapp.services.MovieServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @ViewModelScoped
    @Provides
    fun provideMovieRepository(service: MovieServices): MovieRepository {
        return DefaultMovieRepository(service)
    }
}