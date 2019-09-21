package io.nanodegree.andrea.popularmovies.data.retrofit.response

import com.google.gson.annotations.SerializedName

import io.nanodegree.andrea.popularmovies.data.model.Movie

data class MovieContainer (@field:SerializedName("results") val movieList: List<Movie>?)

