package io.nanodegree.andrea.popularmovies.data.model

import com.google.gson.annotations.SerializedName

data class Video(

        @SerializedName("id")
        val id: String? = null,

        @SerializedName("key")
        val key: String? = null,

        @SerializedName("name")
        val name: String? = null,

        @SerializedName("site")
        val site: String? = null,

        @SerializedName("type")
        val type: String? = null
)
