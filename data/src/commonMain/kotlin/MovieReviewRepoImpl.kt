package com.jetbrains.handson.mpp.mobile

class MovieReviewRepoImpl(private val api: MovieApi) : MovieReviewRepo{
    override suspend fun getMovieReviews(id: String): Either<ApiError, List<ReviewContainer.Review>> = api.getMovieReviews(id)
    override suspend fun getMovieTrailers(id: String): Either<ApiError, List<VideoContainer.Video>> = api.getMovieTrailers(id)
}