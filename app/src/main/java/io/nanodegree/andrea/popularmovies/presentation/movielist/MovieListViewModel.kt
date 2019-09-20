package io.nanodegree.andrea.popularmovies.presentation.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import io.nanodegree.andrea.popularmovies.data.model.Movie
import io.nanodegree.andrea.popularmovies.domain.usecase.GetMovieListUseCase

internal class MovieListViewModel(private val getMovieListUseCase: GetMovieListUseCase) : ViewModel() {

    private val stateMutableLiveData = MutableLiveData<ViewState>()
    val stateLiveData = stateMutableLiveData as LiveData<ViewState>

    fun loadMovies() {

        sendAction(Action.MovieListLoading)

        viewModelScope.launch {
            getMovieListUseCase.getMoviesList()
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
        is Action.MovieListLoading -> ViewState(
                isLoading = true,
                isError = false,
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
        object MovieListLoading : Action()
    }
}