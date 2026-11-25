package com.example.startercode.data.model

import com.google.gson.annotations.SerializedName
import java.util.Locale

/**
 * Represents a movie returned by TMDB list endpoints such as trending and search.
 */
data class Movie(
    val id: Int,
    val title: String,
    val overview: String = "",
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    @SerializedName("release_date") val releaseDate: String? = null,
    @SerializedName("vote_average") val voteAverage: Double = 0.0,
    @SerializedName("genre_ids") val genreIds: List<Int> = emptyList(),
) {
    /**
     * Builds the full TMDB image URL for this movie's poster, or null if unavailable.
     */
    fun posterUrl(size: String = DEFAULT_POSTER_SIZE): String? {
        val path = posterPath?.trim()?.takeIf { it.isNotEmpty() } ?: return null
        val normalizedPath = if (path.startsWith("/")) path else "/$path"
        return "$IMAGE_BASE_URL$size$normalizedPath"
    }

    /**
     * Extracts the four-digit release year from [releaseDate], or "TBA" if missing.
     */
    fun releaseYear(): String {
        val trimmed = releaseDate?.trim().orEmpty()
        return if (trimmed.length >= 4) trimmed.substring(0, 4) else "TBA"
    }

    /**
     * Formats [voteAverage] as a short rating label for list cards.
     */
    fun formattedRating(): String {
        return if (voteAverage > 0.0) {
            String.format(Locale.US, "%.1f / 10", voteAverage)
        } else {
            "N/A"
        }
    }

    companion object {
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"
        const val DEFAULT_POSTER_SIZE = "w500"
    }
}
