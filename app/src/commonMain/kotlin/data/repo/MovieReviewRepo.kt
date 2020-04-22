package data.repo

import data.api.Either
import data.api.MovieApi
import data.api.error.ApiError
import data.model.Review
import data.model.Video

class MovieReviewRepo(private val api: MovieApi) {
    suspend fun getMovieReviews(id: String): Either<ApiError, List<Review>> = api.getMovieReviews(id)
    suspend fun getMovieTrailers(id: String): Either<ApiError, List<Video>> = api.getMovieTrailers(id)
}