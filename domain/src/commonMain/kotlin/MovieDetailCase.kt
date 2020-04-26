package com.jetbrains.handson.mpp.mobile

class MovieListCase(private val popularMovieListRepo: PopularMovieListRepo) {
    suspend fun getPopularMoviesRepo(): Either<ApiError, List<MovieContainer.Movie>> = popularMovieListRepo.getPopularMovies()
}

class MovieDetailCase(private val movieReviewRepo: MovieReviewRepo) {
    suspend fun getMovieReviewsRepo(id: String): Either<ApiError, List<ReviewContainer.Review>> = movieReviewRepo.getMovieReviews(id)
    suspend fun getMovieTrailersRepo(id: String): Either<ApiError, List<VideoContainer.Video>> = movieReviewRepo.getMovieTrailers(id)
}

object CaseProvider {
    fun getMovieListCase() = MovieListCase(PopularMovieListRepo(MovieApi()))
    fun getMovieDetailCase() = MovieDetailCase(MovieReviewRepo(MovieApi()))
}