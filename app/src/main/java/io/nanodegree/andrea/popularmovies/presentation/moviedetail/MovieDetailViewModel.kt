package io.nanodegree.andrea.popularmovies.presentation.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.nanodegree.andrea.popularmovies.data.model.Review
import io.nanodegree.andrea.popularmovies.data.model.Video
import io.nanodegree.andrea.popularmovies.domain.usecase.GetMovieDetailUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

internal class MovieDetailViewModel(private val getMovieDetailUseCase: GetMovieDetailUseCase) : ViewModel() {

    private val stateMutableLiveData = MutableLiveData<ViewState>()
    val stateLiveData = stateMutableLiveData as LiveData<ViewState>

    fun loadRelatedData(id: String) {
        val trailers = viewModelScope.async { getMovieDetailUseCase.getTrailerList(id) }
        val reviews = viewModelScope.async { getMovieDetailUseCase.getReviewList(id) }

        viewModelScope.launch {
            try {
                sendAction(Action.MovieListLoadingSuccess(trailers.await(), reviews.await()))
            } catch (e: Exception) {
                // Do nothing, the views are still GONE if call is not successful
            }
        }

    }

    private fun sendAction(action: Action) {
        stateMutableLiveData.value = onReduceState(action)
    }

    private fun onReduceState(viewAction: Action) = when (viewAction) {
        is Action.MovieListLoadingSuccess -> ViewState(
                trailers = viewAction.trailers,
                reviews = viewAction.reviews
        )
        is Action.MovieListLoadingFailure -> ViewState(
                trailers = listOf(),
                reviews = listOf()
        )
    }

    internal data class ViewState(
            val trailers: List<Video> = listOf(),
            val reviews: List<Review> = listOf()
    )

    internal sealed class Action {
        class MovieListLoadingSuccess(val trailers: List<Video>, val reviews: List<Review>) : Action()
        object MovieListLoadingFailure : Action()
    }
}
