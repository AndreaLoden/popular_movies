package io.nanodegree.andrea.popularmoviess

import android.os.Bundle
import android.transition.Fade
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.jetbrains.handson.mpp.mobile.MovieContainer
import io.nanodegree.andrea.popularmovies.DetailsTransition
import io.nanodegree.andrea.popularmovies.HostActivity
import io.nanodegree.andrea.popularmovies.presentation.MovieNavigator
import io.nanodegree.andrea.popularmovies.presentation.moviedetail.MovieDetailFragment
import io.nanodegree.andrea.popularmovies.presentation.movielist.MovieListFragment
import kotlinx.android.synthetic.main.activity_host.*

class MainActivity : AppCompatActivity(), MovieNavigator,
    HostActivity {

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
     * Implementation of [HostActivity]
     *********************************************************************************************/
    override fun setToolbarTitle(title: String) {
        toolbar.title = title
    }

    /**********************************************************************************************
     * Implementation of [MovieNavigator]
     *********************************************************************************************/


    override fun navigateToMovieDetailFragment(
        movie: MovieContainer.Movie,
        originFragment: Fragment,
        posterImageView: View
    ) {
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
            .addSharedElement(
                posterImageView, ViewCompat.getTransitionName(posterImageView)
                    ?: ""
            )
            .commit()
    }
}
