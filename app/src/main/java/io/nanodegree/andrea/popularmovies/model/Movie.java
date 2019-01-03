package io.nanodegree.andrea.popularmovies.model;

import com.squareup.moshi.Json;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Andrea Loddo (andrea@evenly.io) on 11.05.2018
 * for Evenly GmbH
 * <p>
 * Copyright (c) 2018 Evenly GmbH,
 * all rights reserved
 */
@Entity
public class Movie implements Serializable {

    private static final String MOVIES_DB_BASE_THUMBNAIL_URL = "http:/image.tmdb.org/t/p/w500";

    @NonNull
    @Json(name = "id")
    @PrimaryKey
    public String id;

    @Json(name = "title")
    public String originalTitle;

    @Json(name = "poster_path")
    public String imageThumbnailUrl;

    @Json(name = "overview")
    public String plotSynopsis;

    @Json(name = "vote_average")
    public String userRating;

    @Json(name = "release_date")
    public String releaseDate;

    public Movie(String id, String originalTitle, String imageThumbnailUrl, String plotSynopsis, String userRating, String releaseDate) {
        this.id = id;
        this.originalTitle = originalTitle;
        this.imageThumbnailUrl = imageThumbnailUrl;
        this.plotSynopsis = plotSynopsis;
        this.userRating = userRating;
        this.releaseDate = releaseDate;
    }

    public String getFormattedImageThumbnailUrl() {
        return MOVIES_DB_BASE_THUMBNAIL_URL + imageThumbnailUrl;
    }
}
