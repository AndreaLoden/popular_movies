package io.popularmovies.kmmp.presentation

import android.view.View
import androidx.fragment.app.Fragment
import io.popularmovies.kmmp.MovieContainer

interface MovieNavigator {
    fun navigateToMovieDetailFragment(movie: MovieContainer.Movie, originFragment: Fragment, posterImageView: View)
}