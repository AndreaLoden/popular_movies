package java.io.nanodegree.popularmovies.feature.movie.data.repository

import java.io.nanodegree.popularmovies.feature.movie.data.retrofit.MovieDbClient
import java.io.nanodegree.popularmovies.feature.movie.data.retrofit.PopularMoviesService
import java.io.nanodegree.popularmovies.feature.movie.data.retrofit.response.MovieContainer

class MovieRepository {

    var client: PopularMoviesService = MovieDbClient.popularMoviesService

    suspend fun getPopularMovies(): MovieContainer = client.listPopularMovies()
}