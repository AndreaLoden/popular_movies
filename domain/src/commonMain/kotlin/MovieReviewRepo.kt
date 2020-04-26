package com.jetbrains.handson.mpp.mobile

class MovieReviewRepo(private val api: MovieApi) {
    suspend fun getMovieReviews(id: String): Either<ApiError, List<ReviewContainer.Review>> = api.getMovieReviews(id)
    suspend fun getMovieTrailers(id: String): Either<ApiError, List<VideoContainer.Video>> = api.getMovieTrailers(id)
}