package com.jetbrains.handson.mpp.mobile

class PopularMovieListRepo(private val api: MovieApi) {
    suspend fun getPopularMovies(): Either<ApiError, List<MovieContainer.Movie>> = api.getPopularMovies()
}