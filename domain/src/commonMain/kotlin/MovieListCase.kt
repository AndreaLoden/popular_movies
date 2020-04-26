package com.jetbrains.handson.mpp.mobile

class MovieListCase(/*private val popularMovieListRepo: PopularListMovieRepo*/) {
    suspend fun getPopularMoviesRepo(): Either<ApiError, List<MovieContainer.Movie>> =
        TODO()
    //popularMovieListRepo.getPopularMovies()
}