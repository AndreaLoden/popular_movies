package io.nanodegree.andrea.popularmovies.model;

import com.squareup.moshi.Json;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Andrea Loddo (andrea@evenly.io) on 20.12.2018
 * for Evenly GmbH
 * <p>
 * Copyright (c) 2018 Evenly GmbH,
 * all rights reserved
 */
public class VideoContainer {

    private static final String TRAILER_KEYWORD = "trailer";
    private static final String YOUTUBE_KEYWORD = "youtube";

    @Json(name = "results")
    private List<Video> videoList;

    public List<Video> getYoutubeTrailers() {

        List<Video> tempVideoList = new ArrayList<>(videoList);

        if (tempVideoList.size() > 0) {

            for (Iterator<Video> videoIterator = videoList.listIterator(); videoIterator.hasNext(); ) {
                Video nextVideo = videoIterator.next();

                if (!nextVideo.type.contains(TRAILER_KEYWORD) || !nextVideo.site.contains(YOUTUBE_KEYWORD)) {
                    videoIterator.remove();
                }
            }

        }

        return tempVideoList;
    }
}
