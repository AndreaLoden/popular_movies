package io.nanodegree.andrea.popularmovies.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by Andrea Loddo (andrea@evenly.io) on 20.12.2018
 * for Evenly GmbH
 *
 *
 * Copyright (c) 2018 Evenly GmbH,
 * all rights reserved
 */
class VideoContainer(@SerializedName("results")
                     private val videoList: MutableList<Video>? = null) {

    fun getYoutubeTrailers(): List<Video>? {

        val tempVideoList = ArrayList(videoList!!)

        if (tempVideoList.size > 0) {

            val videoIterator = videoList.listIterator()
            while (videoIterator.hasNext()) {
                val (_, _, _, site, type) = videoIterator.next()

                if (!(type == TRAILER_KEYWORD && site == YOUTUBE_KEYWORD)) {
                    videoIterator.remove()
                }
            }
        }

        return tempVideoList
    }

    companion object {
        private const val TRAILER_KEYWORD = "Trailer"
        private const val YOUTUBE_KEYWORD = "YouTube"
    }
}
