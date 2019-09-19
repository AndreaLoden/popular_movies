package java.io.nanodegree.popularmovies.feature.movie.domain.repository

import java.io.nanodegree.popularmovies.feature.movie.data.retrofit.response.MovieContainer

internal interface MovieListRepository {
    suspend fun getPopularMovies(): MovieContainer
}