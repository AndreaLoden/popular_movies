package java.io.nanodegree.popularmovies.feature.movie.presentation

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.nanodegree.andrea.popularmovies.feature.movie.R

/**
 * A placeholder fragment containing a simple view.
 */
class MovieListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragmentn_movie_list, container, false)
    }
}
