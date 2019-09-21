package io.nanodegree.andrea.popularmovies.domain.usecase

import io.nanodegree.andrea.popularmovies.data.model.Movie
import io.nanodegree.andrea.popularmovies.domain.repository.MovieListRepository

internal class GetMovieListUseCase(private val movieRepository: MovieListRepository) {

    suspend fun getMoviesList(): List<Movie> {
        return movieRepository
                .getPopularMovies()
                .movieList
                ?.filter { !it.imageThumbnailUrl.isNullOrBlank() } ?: listOf()
    }
}