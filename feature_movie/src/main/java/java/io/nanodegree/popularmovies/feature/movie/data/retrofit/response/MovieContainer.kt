package java.io.nanodegree.popularmovies.feature.movie.data.retrofit.response

import com.google.gson.annotations.SerializedName

import java.io.nanodegree.popularmovies.feature.movie.data.model.Movie

/**
 * Created by Andrea Loddo (andrea@evenly.io) on 20.12.2018
 * for Evenly GmbH
 *
 *
 * Copyright (c) 2018 Evenly GmbH,
 * all rights reserved
 */
data class MovieContainer (@field:SerializedName("results") val movieList: List<Movie>?)

