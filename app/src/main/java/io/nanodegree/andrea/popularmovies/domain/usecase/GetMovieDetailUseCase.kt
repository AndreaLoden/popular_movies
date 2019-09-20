package io.nanodegree.andrea.popularmovies.domain.usecase

import io.nanodegree.andrea.popularmovies.data.model.Review
import io.nanodegree.andrea.popularmovies.data.model.Video
import io.nanodegree.andrea.popularmovies.domain.repository.MovieDetailRepository

internal class GetMovieDetailUseCase(private val movieDetailRepository: MovieDetailRepository) {

    suspend fun getTrailerList(id: String): List<Video> {
        return movieDetailRepository
                .getTrailers(id)
                .getYoutubeTrailers() ?: listOf()
    }

    suspend fun getReviewList(id: String): List<Review> {
        return movieDetailRepository
                .getReviews(id)
                .reviewList ?: listOf()
    }
}