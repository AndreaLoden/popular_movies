package io.popularmovies.kmmp

data class MovieDetailState(
    val movieReviewsResponse: Either<ApiError, List<ReviewContainer.Review>>,
    val movieTrailers: Either<ApiError, List<VideoContainer.Video>>
)

interface MovieDetailView {
    fun showState(state: MovieDetailState)
}