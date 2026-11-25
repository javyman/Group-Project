package com.example.startercode.data.remote

import com.example.startercode.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit contract for The Movie Database (TMDB) REST API v3.
 *
 * Teammates can extend this interface for `/3/search/movie`, `/3/movie/{movie_id}`,
 * `/3/movie/{movie_id}/credits`, and `/3/movie/top_rated`.
 */
interface TmdbApiService {
    @GET("trending/movie/week")
    suspend fun getTrendingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1,
    ): MovieResponse
}
