package io.popularmovies.kmmp

class MovieListCase(private val popularMovieListRepo: PopularListMovieRepo) {
    suspend fun getPopularMoviesRepo(): Either<ApiError, List<MovieContainer.Movie>> =
        popularMovieListRepo.getPopularMovies()
}