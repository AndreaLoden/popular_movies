package java.io.nanodegree.popularmovies.feature.movie.presentation.movielist

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.nanodegree.andrea.popularmovies.feature.movie.R
import java.io.nanodegree.popularmovies.feature.movie.data.repository.MovieRepository
import java.io.nanodegree.popularmovies.feature.movie.domain.usecase.GetPopularMoviesListUseCase

/**
 * A placeholder fragment containing a simple view.
 */
class MovieListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragmentn_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewmodel = MovieListViewModel(GetPopularMoviesListUseCase(MovieRepository()))
        viewmodel.loadMovies()
    }
}
