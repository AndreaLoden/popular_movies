package java.io.nanodegree.popularmovies.feature.movie.presentation

import java.io.nanodegree.popularmovies.feature.movie.data.model.Movie

interface MovieNavigator {
    fun navigateToMovieDetailFragment(movie: Movie)
}