package com.yassir.moviesapp.di.modules

import android.content.Context
import com.yassir.moviesapp.utils.ResourceHandler
import com.yassir.moviesapp.wrappers.AndroidResourceHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthDependencyModule {
    @Singleton
    @Provides
    fun provideResourceHandler(@ApplicationContext context: Context): ResourceHandler {
        return AndroidResourceHandler(context)
    }
}