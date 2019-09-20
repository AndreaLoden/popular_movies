package io.nanodegree.andrea.popularmovies

import android.os.Bundle
import android.transition.Fade
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_host.*
import io.nanodegree.andrea.popularmovies.data.model.Movie
import io.nanodegree.andrea.popularmovies.presentation.MovieNavigator
import io.nanodegree.andrea.popularmovies.presentation.moviedetail.MovieDetailFragment
import io.nanodegree.andrea.popularmovies.presentation.movielist.MovieListFragment


class HostActivity : AppCompatActivity(), MovieNavigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, MovieListFragment())
                    .commit()
        }
    }

    /**********************************************************************************************
     * Implementation of [MovieNavigator]
     *********************************************************************************************/

    override fun navigateToMovieDetailFragment(movie: Movie, originFragment: Fragment, transitionView: View) {
        val movieDetailFragment = MovieDetailFragment.newInstance(movie)

        movieDetailFragment.sharedElementEnterTransition = DetailsTransition()
        movieDetailFragment.enterTransition = Fade()

        originFragment.exitTransition = Fade()
        originFragment.sharedElementReturnTransition = DetailsTransition()

        supportFragmentManager
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container, movieDetailFragment, movie.id)
                .addToBackStack(movie.id)
                .addSharedElement(transitionView, ViewCompat.getTransitionName(transitionView)
                        ?: "")
                .commit()
    }
}
