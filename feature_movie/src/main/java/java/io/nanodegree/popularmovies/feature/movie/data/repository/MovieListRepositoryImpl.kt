package java.io.nanodegree.popularmovies.feature.movie.data.repository

import java.io.nanodegree.popularmovies.feature.movie.data.retrofit.PopularMoviesService
import java.io.nanodegree.popularmovies.feature.movie.data.retrofit.response.MovieContainer
import java.io.nanodegree.popularmovies.feature.movie.domain.repository.MovieListRepository

class MovieListRepositoryImpl(private val client: PopularMoviesService) : MovieListRepository {

    override suspend fun getPopularMovies(): MovieContainer = client.listPopularMovies()
}