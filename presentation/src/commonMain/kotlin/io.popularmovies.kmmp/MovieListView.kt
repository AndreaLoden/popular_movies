package io.popularmovies.kmmp

data class MovieState(val popularMoviesResponse: Either<ApiError, List<MovieContainer.Movie>>)

interface MovieListView {
    fun showState(state: MovieState)
}