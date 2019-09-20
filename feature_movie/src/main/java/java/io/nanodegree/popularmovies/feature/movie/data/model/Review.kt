package java.io.nanodegree.popularmovies.feature.movie.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Andrea Loddo (andrea@evenly.io) on 20.12.2018
 * for Evenly GmbH
 *
 *
 * Copyright (c) 2018 Evenly GmbH,
 * all rights reserved
 */
data class Review(@SerializedName("id")
                  val id: String? = null,

                  @SerializedName("author")
                  val author: String? = null,

                  @SerializedName("content")
                  val content: String? = null,

                  @SerializedName("url")
                  val url: String? = null)