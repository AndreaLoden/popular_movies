package java.io.nanodegree.popularmovies.feature.movie.data.repository

import java.io.nanodegree.popularmovies.feature.movie.data.retrofit.MoviesService
import java.io.nanodegree.popularmovies.feature.movie.data.retrofit.response.MovieContainer
import java.io.nanodegree.popularmovies.feature.movie.domain.repository.MovieListRepository

class MovieListRepositoryImpl(private val client: MoviesService) : MovieListRepository {

    override suspend fun getPopularMovies(): MovieContainer = client.listPopularMovies()
    override suspend fun getTopRatedMovies(): MovieContainer = client.listTopRatedMovies()
}