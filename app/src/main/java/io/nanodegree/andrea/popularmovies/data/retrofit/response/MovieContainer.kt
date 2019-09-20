package io.nanodegree.andrea.popularmovies.data.retrofit.response

import com.google.gson.annotations.SerializedName

import io.nanodegree.andrea.popularmovies.data.model.Movie

/**
 * Created by Andrea Loddo (andrea@evenly.io) on 20.12.2018
 * for Evenly GmbH
 *
 *
 * Copyright (c) 2018 Evenly GmbH,
 * all rights reserved
 */
data class MovieContainer (@field:SerializedName("results") val movieList: List<Movie>?)

