package io.nanodegree.andrea.popularmovies.data.model

import com.google.gson.annotations.SerializedName

data class ReviewsContainer(
        @SerializedName("results")
        val reviewList: List<Review>? = null
)
