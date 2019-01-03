package io.nanodegree.andrea.popularmovies.model;

import com.squareup.moshi.Json;

/**
 * Created by Andrea Loddo (andrea@evenly.io) on 20.12.2018
 * for Evenly GmbH
 * <p>
 * Copyright (c) 2018 Evenly GmbH,
 * all rights reserved
 */
public class Review {

    @Json(name = "id")
    public String id;

    @Json(name = "author")
    public String author;

    @Json(name = "content")
    public String content;

    @Json(name = "url")
    public String url;
}
