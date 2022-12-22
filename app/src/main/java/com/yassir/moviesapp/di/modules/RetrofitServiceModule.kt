package com.yassir.moviesapp.di.modules

import com.yassir.moviesapp.services.MovieServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit


@Module
@InstallIn(ViewModelComponent::class)
class RetrofitServiceModule {

    @ViewModelScoped
    @Provides
    fun provideMovieServices(retrofit: Retrofit): MovieServices {
        return retrofit.create(MovieServices::class.java)
    }


}