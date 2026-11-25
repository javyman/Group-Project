package com.example.startercode

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.startercode.data.remote.TmdbClient
import com.example.startercode.ui.FeedState
import com.example.startercode.ui.FeedStateResolver
import com.example.startercode.ui.adapter.MovieAdapter
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var rvTrendingMovies: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var tvStatus: TextView
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvTrendingMovies = findViewById(R.id.rvTrendingMovies)
        progressBar = findViewById(R.id.progressBar)
        tvStatus = findViewById(R.id.tvStatus)

        movieAdapter = MovieAdapter(
            onMovieClick = { movie ->
                Toast.makeText(
                    this,
                    getString(R.string.details_stub_toast, movie.title),
                    Toast.LENGTH_SHORT,
                ).show()
            },
            onMovieLongClick = { movie ->
                Toast.makeText(
                    this,
                    getString(R.string.bookmark_toast, movie.title),
                    Toast.LENGTH_SHORT,
                ).show()
            },
        )

        rvTrendingMovies.layoutManager = LinearLayoutManager(this)
        rvTrendingMovies.adapter = movieAdapter

        loadTrendingMovies()
    }

    private fun loadTrendingMovies() {
        val apiKey = BuildConfig.TMDB_API_KEY
        if (!FeedStateResolver.hasApiKey(apiKey)) {
            renderState(FeedState.MissingApiKey)
            return
        }

        renderState(FeedState.Loading)
        lifecycleScope.launch {
            runCatching {
                TmdbClient.api.getTrendingMovies(apiKey = apiKey)
            }.onSuccess { response ->
                renderState(FeedStateResolver.fromResults(response.results))
            }.onFailure {
                renderState(FeedState.Error)
            }
        }
    }

    private fun renderState(state: FeedState) {
        progressBar.visibility = if (state is FeedState.Loading) View.VISIBLE else View.GONE
        when (state) {
            FeedState.Loading -> {
                tvStatus.visibility = View.GONE
                rvTrendingMovies.visibility = View.GONE
            }
            FeedState.MissingApiKey -> {
                rvTrendingMovies.visibility = View.GONE
                tvStatus.visibility = View.VISIBLE
                tvStatus.setText(R.string.missing_api_key_message)
            }
            FeedState.Empty -> {
                rvTrendingMovies.visibility = View.GONE
                tvStatus.visibility = View.VISIBLE
                tvStatus.setText(R.string.empty_movies_message)
            }
            FeedState.Error -> {
                rvTrendingMovies.visibility = View.GONE
                tvStatus.visibility = View.VISIBLE
                tvStatus.setText(R.string.error_loading_movies)
            }
            is FeedState.Content -> {
                tvStatus.visibility = View.GONE
                rvTrendingMovies.visibility = View.VISIBLE
                movieAdapter.submitList(state.movies)
            }
        }
    }
}
