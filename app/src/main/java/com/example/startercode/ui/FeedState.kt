package com.example.startercode.ui

import com.example.startercode.data.model.Movie

/**
 * Represents the barebones display state of a movie list screen.
 */
sealed interface FeedState {
    data object Loading : FeedState
    data object MissingApiKey : FeedState
    data object Empty : FeedState
    data object Error : FeedState
    data class Content(val movies: List<Movie>) : FeedState
}

/**
 * Pure helper functions for deciding the next [FeedState] without Android framework dependencies.
 */
object FeedStateResolver {
    fun hasApiKey(apiKey: String?): Boolean = !apiKey.isNullOrBlank()

    fun fromResults(movies: List<Movie>): FeedState {
        return if (movies.isEmpty()) FeedState.Empty else FeedState.Content(movies)
    }
}
