package com.example.startercode

import com.example.startercode.data.model.Movie
import com.example.startercode.ui.FeedState
import com.example.startercode.ui.FeedStateResolver
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class FeedStateResolverTest {
    @Test
    fun hasApiKey_rejectsBlankAndNull() {
        assertFalse(FeedStateResolver.hasApiKey(null))
        assertFalse(FeedStateResolver.hasApiKey(""))
        assertFalse(FeedStateResolver.hasApiKey("   "))
        assertTrue(FeedStateResolver.hasApiKey("demo_key"))
    }

    @Test
    fun fromResults_returnsEmptyOrContent() {
        assertEquals(FeedState.Empty, FeedStateResolver.fromResults(emptyList()))

        val movies = listOf(Movie(id = 1, title = "Dune"))
        assertEquals(FeedState.Content(movies), FeedStateResolver.fromResults(movies))
    }
}
