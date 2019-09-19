package java.io.nanodegree.popularmovies.feature.movie.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Andrea Loddo (andrea@evenly.io) on 11.05.2018
 * for Evenly GmbH
 *
 *
 * Copyright (c) 2018 Evenly GmbH,
 * all rights reserved
 */
//@Entity
data class Movie(@field:SerializedName("id") val id: String?,
                 @field:SerializedName("poster_path") val imageThumbnailUrl: String?) {

    fun getFormattedImageThumbnailUrl(): String {
        return "http:/image.tmdb.org/t/p/w500" + imageThumbnailUrl
    }
}

/*


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
    }*/
