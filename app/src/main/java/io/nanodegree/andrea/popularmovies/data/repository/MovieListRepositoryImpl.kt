package io.nanodegree.andrea.popularmovies.data.repository

import io.nanodegree.andrea.popularmovies.data.retrofit.MoviesService
import io.nanodegree.andrea.popularmovies.data.retrofit.response.MovieContainer
import io.nanodegree.andrea.popularmovies.domain.repository.MovieListRepository

class MovieListRepositoryImpl(private val client: MoviesService) : MovieListRepository {

    override suspend fun getPopularMovies(): MovieContainer = client.listPopularMovies()
}