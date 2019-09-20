package io.nanodegree.andrea.popularmovies.data.repository

import io.nanodegree.andrea.popularmovies.data.model.ReviewsContainer
import io.nanodegree.andrea.popularmovies.data.model.VideoContainer
import io.nanodegree.andrea.popularmovies.data.retrofit.MoviesService
import io.nanodegree.andrea.popularmovies.domain.repository.MovieDetailRepository

class MovieDetailRepositoryImpl(private val client: MoviesService) : MovieDetailRepository {
    override suspend fun getTrailers(id: String): VideoContainer {
        return client.getMovieVideos(id)
    }

    override suspend fun getReviews(id: String): ReviewsContainer {
        return client.getMovieReviews(id)
    }

}