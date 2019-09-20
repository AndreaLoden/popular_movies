package io.nanodegree.andrea.popularmovies.domain.repository

import io.nanodegree.andrea.popularmovies.data.retrofit.response.MovieContainer

internal interface MovieListRepository {
    suspend fun getPopularMovies(): MovieContainer
}