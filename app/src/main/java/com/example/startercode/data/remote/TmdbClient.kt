package com.example.startercode.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Shared Retrofit client for TMDB API requests.
 */
object TmdbClient {
    const val BASE_URL = "https://api.themoviedb.org/3/"

    val api: TmdbApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TmdbApiService::class.java)
    }
}
