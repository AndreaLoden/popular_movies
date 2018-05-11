package io.nanodegree.andrea.popularmovies.model;

/**
 * Created by Andrea Loddo (andrea@evenly.io) on 11.05.2018
 * for Evenly GmbH
 * <p>
 * Copyright (c) 2018 Evenly GmbH,
 * all rights reserved
 */
public class Movie {
    private String originalTitle;
    private String imageThumbnailUrl;
    private String plotSynopsis;
    private String userRating;
    private String releaseDate;

    public Movie(String originalTitle, String imageThumbnailUrl, String plotSynopsis, String userRating, String releaseDate) {
        this.originalTitle = originalTitle;
        this.imageThumbnailUrl = imageThumbnailUrl;
        this.plotSynopsis = plotSynopsis;
        this.userRating = userRating;
        this.releaseDate = releaseDate;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getImageThumbnailUrl() {
        return imageThumbnailUrl;
    }

    public String getPlotSynopsis() {
        return plotSynopsis;
    }

    public String getUserRating() {
        return userRating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
