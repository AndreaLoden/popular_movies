package io.nanodegree.andrea.popularmovies.model;

import com.squareup.moshi.Json;

import java.io.Serializable;

/**
 * Created by Andrea Loddo (andrea@evenly.io) on 11.05.2018
 * for Evenly GmbH
 * <p>
 * Copyright (c) 2018 Evenly GmbH,
 * all rights reserved
 */
public class Movie implements Serializable {

    private static final String MOVIES_DB_BASE_THUMBNAIL_URL = "http:/image.tmdb.org/t/p/w500";

    @Json(name = "id")
    public String id;

    @Json(name = "title")
    public String originalTitle;

    @Json(name = "poster_path")
    private String imageThumbnailUrl;

    @Json(name = "overview")
    public String plotSynopsis;

    @Json(name = "vote_average")
    public String userRating;

    @Json(name = "release_date")
    public String releaseDate;

    public String getImageThumbnailUrl() {
        return MOVIES_DB_BASE_THUMBNAIL_URL + imageThumbnailUrl;
    }
}
