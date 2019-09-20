package io.nanodegree.andrea.popularmovies.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Andrea Loddo (andrea@evenly.io) on 20.12.2018
 * for Evenly GmbH
 *
 *
 * Copyright (c) 2018 Evenly GmbH,
 * all rights reserved
 */
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
