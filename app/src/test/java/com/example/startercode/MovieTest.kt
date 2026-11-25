package com.example.startercode

import com.example.startercode.data.model.Movie
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class MovieTest {
    @Test
    fun posterUrl_formatsValidAndMissingPaths() {
        val withLeadingSlash = Movie(id = 1, title = "Dune", posterPath = "/abc123.jpg")
        val withoutLeadingSlash = Movie(id = 2, title = "Arrival", posterPath = "xyz.jpg")
        val blankPath = Movie(id = 3, title = "Missing", posterPath = "  ")
        val nullPath = Movie(id = 4, title = "Null", posterPath = null)

        assertEquals("https://image.tmdb.org/t/p/w500/abc123.jpg", withLeadingSlash.posterUrl())
        assertEquals("https://image.tmdb.org/t/p/w342/xyz.jpg", withoutLeadingSlash.posterUrl("w342"))
        assertNull(blankPath.posterUrl())
        assertNull(nullPath.posterUrl())
    }

    @Test
    fun releaseYear_extractsYearOrFallsBackToTba() {
        assertEquals("2024", Movie(id = 1, title = "Dune", releaseDate = "2024-02-27").releaseYear())
        assertEquals("TBA", Movie(id = 2, title = "Upcoming", releaseDate = "").releaseYear())
        assertEquals("TBA", Movie(id = 3, title = "Unknown", releaseDate = null).releaseYear())
    }

    @Test
    fun formattedRating_formatsPositiveScoresAndHandlesZero() {
        assertEquals("8.3 / 10", Movie(id = 1, title = "Dune", voteAverage = 8.26).formattedRating())
        assertEquals("N/A", Movie(id = 2, title = "New", voteAverage = 0.0).formattedRating())
    }
}
