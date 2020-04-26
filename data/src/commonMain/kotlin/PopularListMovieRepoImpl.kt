package com.jetbrains.handson.mpp.mobile

class PopularListMovieRepoImpl(private val api: MovieApi) : PopularListMovieRepo {
    override suspend fun getPopularMovies(): Either<ApiError, List<MovieContainer.Movie>> = api.getPopularMovies()
}