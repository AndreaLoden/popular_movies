package io.nanodegree.andrea.popularmovies.presentation.movielist.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.nanodegree.andrea.popularmovies.R
import io.nanodegree.andrea.popularmovies.data.model.Movie
import java.util.*

/**
 * Created by Andrea Loddo (andrea@evenly.io) on 11.05.2018
 * for Evenly GmbH
 *
 *
 * Copyright (c) 2018 Evenly GmbH,
 * all rights reserved
 */
internal class MovieListAdapter(private val movieClickListener: MovieClickListener) : RecyclerView.Adapter<MovieListAdapter.MovieItemViewHolder>() {

    interface MovieClickListener {
        fun onMovieClicked(movie: Movie, transitionView: View)
    }

    private val movies: MutableList<Movie>

    init {
        movies = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val view = inflater.inflate(R.layout.movie_item, parent, false)

        return MovieItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.bind(movies[position])

        val movie = movies[position]
        ViewCompat.setTransitionName(holder.moviePoster, movie.id)
        holder.moviePoster.setOnClickListener{movieClickListener.onMovieClicked(movie, holder.moviePoster)}
    }

    override fun getItemCount(): Int = movies.size

    fun setData(movies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)
    }

    fun getData(): ArrayList<Movie> {
        return ArrayList(this.movies)
    }

    internal inner class MovieItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var moviePoster: ImageView = itemView.findViewById(R.id.movie_poster)

        fun bind(movie: Movie) {
            Picasso.get()
                    .load(movie.getFormattedImageThumbnailUrl())
                    .placeholder(R.drawable.poster_placeholder_loading)
                    .error(R.drawable.poster_placeholder_error)
                    .into(moviePoster)
        }

    }
}
