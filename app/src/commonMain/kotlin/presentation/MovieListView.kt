package presentation

import data.api.Either
import data.api.error.ApiError
import data.model.MovieContainer

data class MovieState(val popularMoviesResponse: Either<ApiError, List<MovieContainer.Movie>>)

interface MovieListView {
    fun showState(state: MovieState)
}