package com.example.startercode

import com.example.startercode.data.model.MovieResponse
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Test

class MovieResponseParsingTest {
    @Test
    fun gson_parsesTrendingMovieResponse() {
        val json = """
            {
              "page": 1,
              "results": [
                {
                  "id": 693134,
                  "title": "Dune: Part Two",
                  "overview": "Paul Atreides unites with the Fremen.",
                  "poster_path": "/1pdfLvkbY9ohJlCjQH2CZjjYVvJ.jpg",
                  "backdrop_path": "/xOMo8BRK7PfcJv9JCnx7s5hj0PX.jpg",
                  "release_date": "2024-02-27",
                  "vote_average": 8.3,
                  "genre_ids": [878, 12]
                }
              ],
              "total_pages": 10,
              "total_results": 200
            }
        """.trimIndent()

        val response = Gson().fromJson(json, MovieResponse::class.java)

        assertEquals(1, response.page)
        assertEquals(10, response.totalPages)
        assertEquals(200, response.totalResults)
        assertEquals(1, response.results.size)

        val movie = response.results.first()
        assertEquals(693134, movie.id)
        assertEquals("Dune: Part Two", movie.title)
        assertEquals("2024", movie.releaseYear())
        assertEquals(
            "https://image.tmdb.org/t/p/w500/1pdfLvkbY9ohJlCjQH2CZjjYVvJ.jpg",
            movie.posterUrl(),
        )
        assertEquals(listOf(878, 12), movie.genreIds)
    }
}
