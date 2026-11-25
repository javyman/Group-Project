package com.example.startercode.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.startercode.R
import com.example.startercode.data.model.Movie

/**
 * Reusable RecyclerView adapter for rendering [Movie] cards across feeds and search results.
 */
class MovieAdapter(
    private val onMovieClick: (Movie) -> Unit,
    private val onMovieLongClick: (Movie) -> Unit,
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val movies = mutableListOf<Movie>()

    fun submitList(newMovies: List<Movie>) {
        movies.clear()
        movies.addAll(newMovies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivPoster: ImageView = itemView.findViewById(R.id.ivPoster)
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val tvReleaseYear: TextView = itemView.findViewById(R.id.tvReleaseYear)
        private val tvRating: TextView = itemView.findViewById(R.id.tvRating)

        fun bind(movie: Movie) {
            tvTitle.text = movie.title
            tvReleaseYear.text = movie.releaseYear()
            tvRating.text = movie.formattedRating()

            val posterUrl = movie.posterUrl()
            if (posterUrl != null) {
                Glide.with(itemView)
                    .load(posterUrl)
                    .centerCrop()
                    .into(ivPoster)
            } else {
                Glide.with(itemView).clear(ivPoster)
                ivPoster.setImageDrawable(null)
            }

            itemView.setOnClickListener { onMovieClick(movie) }
            itemView.setOnLongClickListener {
                onMovieLongClick(movie)
                true
            }
        }
    }
}
