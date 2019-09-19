package io.nanodegree.andrea.popularmovies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_host.*
import java.io.nanodegree.popularmovies.feature.movie.data.model.Movie
import java.io.nanodegree.popularmovies.feature.movie.presentation.MovieNavigator
import java.io.nanodegree.popularmovies.feature.movie.presentation.moviedetail.MovieDetailFragment
import java.io.nanodegree.popularmovies.feature.movie.presentation.movielist.MovieListFragment


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

    override fun navigateToMovieDetailFragment(movie: Movie) {
        val movieDetailFragment = MovieDetailFragment()

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, movieDetailFragment)
                .addToBackStack(null)
                .commit();
    }
}
