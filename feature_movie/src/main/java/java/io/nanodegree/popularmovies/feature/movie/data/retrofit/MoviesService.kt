package java.io.nanodegree.popularmovies.feature.movie.data.retrofit

import retrofit2.http.GET
import java.io.nanodegree.popularmovies.feature.movie.data.retrofit.response.MovieContainer

/**
 * Created by Andrea Loddo (andrea@evenly.io) on 20.12.2018
 * for Evenly GmbH
 *
 *
 * Copyright (c) 2018 Evenly GmbH,
 * all rights reserved
 */
interface MoviesService {

    @GET("popular")
    suspend fun listPopularMovies(): MovieContainer
}
