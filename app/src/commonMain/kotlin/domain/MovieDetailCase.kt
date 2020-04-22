package domain

import data.api.Either
import data.api.MovieApi
import data.api.error.ApiError
import data.model.MovieContainer
import data.model.Review
import data.model.Video
import data.repo.MovieReviewRepo
import data.repo.PopularMovieListRepo

class MovieListCase(private val popularMovieListRepo: PopularMovieListRepo) {
    suspend fun getPopularMoviesRepo(): Either<ApiError, List<MovieContainer.Movie>> = popularMovieListRepo.getPopularMovies()
}

class MovieDetailCase(private val movieReviewRepo: MovieReviewRepo) {
    suspend fun getMovieReviewsRepo(id: String): Either<ApiError, List<Review>> = movieReviewRepo.getMovieReviews(id)
    suspend fun getMovieTrailersRepo(id: String): Either<ApiError, List<Video>> = movieReviewRepo.getMovieTrailers(id)
}

object CaseProvider {
    fun getMovieListCase() = MovieListCase(PopularMovieListRepo(MovieApi()))
    fun getMovieDetailCase() = MovieDetailCase(MovieReviewRepo(MovieApi()))
}