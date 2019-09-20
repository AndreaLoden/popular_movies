package io.nanodegree.andrea.popularmovies.presentation.movielist

import android.os.Bundle
import android.os.Handler
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import io.nanodegree.andrea.popularmovies.R
import kotlinx.android.synthetic.main.fragment_movie_list.*
import org.koin.android.viewmodel.ext.android.viewModel
import io.nanodegree.andrea.popularmovies.data.model.Movie
import io.nanodegree.andrea.popularmovies.extensions.observe
import io.nanodegree.andrea.popularmovies.presentation.MovieNavigator
import io.nanodegree.andrea.popularmovies.presentation.movielist.recyclerview.MovieListAdapter
import io.nanodegree.andrea.popularmovies.presentation.movielist.recyclerview.SpacesItemDecoration
import java.util.*

/**
 * A fragment that shows popular movie posters on a recycler view
 */
class MovieListFragment : Fragment(), MovieListAdapter.MovieClickListener {

    private var gridLayoutManager: GridLayoutManager? = null

    private val moviesAdapter by lazy { MovieListAdapter(this) }
    // Lazy Inject ViewModel
    private val movieListViewModel: MovieListViewModel by viewModel()

    /**********************************************************************************************
     * Lifecycle callbacks
     *********************************************************************************************/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postponeEnterTransition()
        setupRecyclerView()

        observe(movieListViewModel.stateLiveData, ::onStateChange)

        if (savedInstanceState == null && moviesAdapter.getData().isEmpty()) {
            movieListViewModel.loadMovies()
        }

        Handler().postDelayed({ startPostponedEnterTransition() }, 300)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(BUNDLE_RECYCLER_LAYOUT, gridLayoutManager?.onSaveInstanceState())
        outState.putSerializable(BUNDLE_MOVIES, moviesAdapter.getData())
        super.onSaveInstanceState(outState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        try {
            (savedInstanceState?.getSerializable(BUNDLE_MOVIES) as ArrayList<Movie>?)?.let { itMovies ->
                onStateChange(MovieListViewModel.ViewState(
                        isLoading = false,
                        isError = false,
                        movies = itMovies
                ))
            }

            val state = savedInstanceState?.getParcelable<Parcelable>(BUNDLE_RECYCLER_LAYOUT)
            gridLayoutManager?.onRestoreInstanceState(state)

        } catch (cce: ClassCastException) {
            Log.d("MovieListFragment", "Unable to deserialize passed movies")
        }

        super.onActivityCreated(savedInstanceState)
    }

    /**********************************************************************************************
     * Implementation of [MovieListAdapter.MovieClickListener]
     *********************************************************************************************/
    override fun onMovieClicked(movie: Movie, transitionView: View) {
        (activity as MovieNavigator).navigateToMovieDetailFragment(movie, this, transitionView)
    }

    /**********************************************************************************************
     * Private methods
     *********************************************************************************************/

    private fun setupRecyclerView() {
        val spanCount = getSpanCount()
        gridLayoutManager = GridLayoutManager(context, spanCount)

        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.movie_item_margin)

        recycler_view_movies.addItemDecoration(SpacesItemDecoration(spanCount, spacingInPixels))
        recycler_view_movies.layoutManager = gridLayoutManager
        recycler_view_movies.setHasFixedSize(true)
        recycler_view_movies.adapter = moviesAdapter
    }

    private fun getSpanCount(): Int {
        return resources.getInteger(R.integer.span_count)
    }

    private fun onStateChange(state: MovieListViewModel.ViewState) {
        moviesAdapter.setData(state.movies)
        moviesAdapter.notifyDataSetChanged()
        progress_bar.visibility = if (state.isLoading) View.VISIBLE else View.GONE
        errorView.visibility = if (state.isError) View.VISIBLE else View.GONE
    }

    companion object {
        private const val BUNDLE_RECYCLER_LAYOUT = "mainactivity.recycler.layout"
        private const val BUNDLE_MOVIES = "mainactivity.data.movies"
    }
}
