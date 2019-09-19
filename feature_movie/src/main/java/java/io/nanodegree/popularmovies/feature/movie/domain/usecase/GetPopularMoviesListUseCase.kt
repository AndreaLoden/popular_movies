package java.io.nanodegree.popularmovies.feature.movie.domain.usecase

import java.io.nanodegree.popularmovies.feature.movie.data.model.Movie
import java.io.nanodegree.popularmovies.feature.movie.domain.repository.MovieListRepository

internal class GetPopularMoviesListUseCase(private val movieRepository: MovieListRepository) {

    suspend fun execute(): List<Movie> {

        return movieRepository
                .getPopularMovies()
                .movieList
                ?.filter { it.imageThumbnailUrl != null } ?: listOf()
    }
}