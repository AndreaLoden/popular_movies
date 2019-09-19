package java.io.nanodegree.popularmovies.feature.movie.presentation

import android.view.View
import androidx.fragment.app.Fragment
import java.io.nanodegree.popularmovies.feature.movie.data.model.Movie

interface MovieNavigator {
    fun navigateToMovieDetailFragment(movie: Movie, originFragment: Fragment, posterImageView: View)
}