package data.repo

import data.api.Either
import data.api.MovieApi
import data.api.error.ApiError
import data.model.MovieContainer

class PopularMovieListRepo(private val api: MovieApi) {
    suspend fun getPopularMovies(): Either<ApiError, List<MovieContainer.Movie>> = api.getPopularMovies()
}