package io.nanodegree.andrea.popularmovies.presentation

import android.view.View
import androidx.fragment.app.Fragment
import io.nanodegree.andrea.popularmovies.data.model.Movie

interface MovieNavigator {
    fun navigateToMovieDetailFragment(movie: Movie, originFragment: Fragment, posterImageView: View)
}