package java.io.nanodegree.popularmovies.feature.movie.presentation.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.io.nanodegree.popularmovies.feature.movie.data.model.Movie
import java.io.nanodegree.popularmovies.feature.movie.domain.usecase.GetPopularMoviesListUseCase

internal class MovieListViewModel(private val getPopularMoviesListUseCase: GetPopularMoviesListUseCase) : ViewModel() {

    private val stateMutableLiveData = MutableLiveData<ViewState>()
    val stateLiveData = stateMutableLiveData as LiveData<ViewState>

    fun loadMovies() {
        viewModelScope.launch {
            getPopularMoviesListUseCase.execute()
                    .also {
                        if (it.isNotEmpty()) {
                            sendAction(Action.MovieListLoadingSuccess(it))
                        } else {
                            sendAction(Action.MovieListLoadingFailure)
                        }
                    }
        }

    }

    fun sendAction(action: Action) {
        stateMutableLiveData.value = onReduceState(action)
    }

    fun onReduceState(viewAction: Action) = when (viewAction) {
        is Action.MovieListLoadingSuccess -> ViewState(
                isLoading = false,
                isError = false,
                movies = viewAction.movies
        )
        is Action.MovieListLoadingFailure -> ViewState(
                isLoading = false,
                isError = true,
                movies = listOf()
        )
    }

    internal data class ViewState(
            val isLoading: Boolean = true,
            val isError: Boolean = false,
            val movies: List<Movie> = listOf()
    )

    internal sealed class Action {
        class MovieListLoadingSuccess(val movies: List<Movie>) : Action()
        object MovieListLoadingFailure : Action()
    }
}