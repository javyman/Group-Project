package com.example.startercode.data.model

import com.google.gson.annotations.SerializedName

/**
 * Paginated movie list response returned by TMDB `/3/trending/movie/week` and `/3/search/movie`.
 */
data class MovieResponse(
    val page: Int = 1,
    val results: List<Movie> = emptyList(),
    @SerializedName("total_pages") val totalPages: Int = 1,
    @SerializedName("total_results") val totalResults: Int = 0,
)
