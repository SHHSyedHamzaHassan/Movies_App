package com.yassir.moviesapp.di.modules

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.yassir.moviesapp.BuildConfig
import com.yassir.moviesapp.interceptors.DefaultInterceptor
import com.yassir.moviesapp.interceptors.NoConnectionInterceptor
import com.yassir.moviesapp.utils.Constants.DataProviderEndPoints.Companion.BASE_URL
import com.yassir.moviesapp.utils.Constants.ThreshHoldValues.Companion.API_TIME_OUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkSetupModule {

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(NoConnectionInterceptor(context))
            .addInterceptor(DefaultInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.NONE
                if (BuildConfig.DEBUG) {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            })
            .callTimeout(API_TIME_OUT, TimeUnit.MINUTES)
            .connectTimeout(API_TIME_OUT, TimeUnit.MINUTES)
            .readTimeout(API_TIME_OUT, TimeUnit.MINUTES)
            .writeTimeout(API_TIME_OUT, TimeUnit.MINUTES)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }
}