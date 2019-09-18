package java.io.nanodegree.popularmovies.feature.movie.data.retrofit.response;

import com.squareup.moshi.Json;

import java.io.nanodegree.popularmovies.feature.movie.data.model.Movie;
import java.util.List;

/**
 * Created by Andrea Loddo (andrea@evenly.io) on 20.12.2018
 * for Evenly GmbH
 * <p>
 * Copyright (c) 2018 Evenly GmbH,
 * all rights reserved
 */
public class MovieContainer {

    @Json(name = "results")
    public List<Movie> movieList;
}
