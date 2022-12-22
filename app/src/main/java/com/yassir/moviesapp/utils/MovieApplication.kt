package com.yassir.moviesapp.utils

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MovieApplication : Application() {
    override fun onCreate() {
        instance = this
        super.onCreate()
    }

    companion object {
        var instance: MovieApplication? = null
            private set
        val context: Context
            get() = instance!!
    }
}