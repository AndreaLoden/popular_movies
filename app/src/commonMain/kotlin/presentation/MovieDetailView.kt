package presentation

import data.api.Either
import data.api.error.ApiError
import data.model.Review
import data.model.Video

data class MovieDetailState(
        val movieReviewsResponse: Either<ApiError, List<Review>>,
        val movieTrailers: Either<ApiError, List<Video>>
)

interface MovieDetailView {
    fun showState(state: MovieDetailState)
}