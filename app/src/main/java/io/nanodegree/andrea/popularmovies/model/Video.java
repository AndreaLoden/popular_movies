package io.nanodegree.andrea.popularmovies.model;

import com.squareup.moshi.Json;

/**
 * Created by Andrea Loddo (andrea@evenly.io) on 20.12.2018
 * for Evenly GmbH
 * <p>
 * Copyright (c) 2018 Evenly GmbH,
 * all rights reserved
 */
public class Video {

    @Json(name = "id")
    public String id;

    @Json(name = "key")
    public String key;

    @Json(name = "name")
    public String name;

    @Json(name = "site")
    public String site;

    @Json(name = "type")
    public String type;
}
