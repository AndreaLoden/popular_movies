package com.jetbrains.handson.mpp.mobile

interface PopularListMovieRepo{
    suspend fun getPopularMovies(): Either<ApiError, List<MovieContainer.Movie>>
}