package java.io.nanodegree.popularmovies.feature.movie.presentation.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import io.nanodegree.andrea.popularmovies.feature.movie.R
import kotlinx.android.synthetic.main.fragment_movie_list.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.io.nanodegree.popularmovies.feature.movie.extensions.observe
import java.io.nanodegree.popularmovies.feature.movie.presentation.movielist.recyclerview.MoviesAdapter

/**
 * A placeholder fragment containing a simple view.
 */
class MovieListFragment : Fragment() {

    private val moviesAdapter by lazy { MoviesAdapter(context!!) }
    // Lazy Inject ViewModel
    private val movieListViewModel: MovieListViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view_movies.apply {
            setHasFixedSize(true)
            layoutManager =
                    GridLayoutManager(
                            context,
                            2
                    )
            adapter = moviesAdapter
        }

        observe(movieListViewModel.stateLiveData, ::onStateChange)
        movieListViewModel.loadMovies()
    }

    private fun onStateChange(state: MovieListViewModel.ViewState) {
        moviesAdapter.setData(state.movies)
        moviesAdapter.notifyDataSetChanged()
        progress_bar.visibility = if (state.isLoading) View.VISIBLE else View.GONE
        errorView.visibility = if (state.isError) View.VISIBLE else View.GONE
    }
}
