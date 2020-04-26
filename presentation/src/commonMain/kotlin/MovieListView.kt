package com.jetbrains.handson.mpp.mobile

data class MovieState(val popularMoviesResponse: Either<ApiError, List<MovieContainer.Movie>>)

interface MovieListView {
    fun showState(state: MovieState)
}