package com.jetbrains.handson.mpp.mobile

class MovieDetailCase(/*private val movieReviewRepo: MovieReviewRepo*/) {
    suspend fun getMovieReviewsRepo(id: String): Either<ApiError, List<ReviewContainer.Review>> =
        TODO()
    //movieReviewRepo.getMovieReviews(id)

    suspend fun getMovieTrailersRepo(id: String): Either<ApiError, List<VideoContainer.Video>> =
        TODO()
    //movieReviewRepo.getMovieTrailers(id)
}