package io.popularmovies.kmmp

interface PopularListMovieRepo {
    suspend fun getPopularMovies(): Either<ApiError, List<MovieContainer.Movie>>
}