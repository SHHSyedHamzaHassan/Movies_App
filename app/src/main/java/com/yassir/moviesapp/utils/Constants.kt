package com.yassir.moviesapp.utils

class Constants {
    class ThreshHoldValues {
        companion object {
            const val API_TIME_OUT: Long = 1
        }
    }

    class DataProviderEndPoints {
        companion object {
            const val BASE_URL = "https://api.themoviedb.org/3/"
            const val IMAGE_URL = "https://image.tmdb.org/t/p/original"
            const val API_KEY_VALUE = "c9856d0cb57c3f14bf75bdc6c063b8f3"
        }
    }

    class APIConstants {
        companion object {
            const val API_KEY = "api_key"
            const val MOVIE_ID = "movie_id"
        }
    }
}