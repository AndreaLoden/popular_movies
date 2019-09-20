package io.nanodegree.andrea.popularmovies.domain.repository

import io.nanodegree.andrea.popularmovies.data.model.ReviewsContainer
import io.nanodegree.andrea.popularmovies.data.model.VideoContainer

internal interface MovieDetailRepository {
    suspend fun getTrailers(id: String): VideoContainer
    suspend fun getReviews(id: String): ReviewsContainer
}