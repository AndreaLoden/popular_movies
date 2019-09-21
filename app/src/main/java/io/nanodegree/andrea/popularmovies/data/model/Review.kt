package io.nanodegree.andrea.popularmovies.data.model

import com.google.gson.annotations.SerializedName

data class Review(@SerializedName("id")
                  val id: String? = null,

                  @SerializedName("author")
                  val author: String? = null,

                  @SerializedName("content")
                  val content: String? = null,

                  @SerializedName("url")
                  val url: String? = null)