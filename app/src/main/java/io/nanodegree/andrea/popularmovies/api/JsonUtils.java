package io.nanodegree.andrea.popularmovies.api;

import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.nanodegree.andrea.popularmovies.model.Movie;

/**
 * Created by Andrea Loddo (andrea@evenly.io) on 11.05.2018
 * for Evenly GmbH
 * <p>
 * Copyright (c) 2018 Evenly GmbH,
 * all rights reserved
 */
public class JsonUtils {

    private static final String MOVIE_RESULTS = "results";
    private static final String MOVIE_TITLE = "title";
    private static final String MOVIE_PATH = "poster_path";
    private static final String MOVIE_OVERVIEW = "overview";
    private static final String MOVIE_AVERAGE = "vote_average";
    private static final String MOVIE_RELEASE_DATE = "release_date";
    private static final String MOVIES_DB_BASE_THUMBNAIL_URL = "http:/image.tmdb.org/t/p/w500";

    @NonNull
    public static List<Movie> parseMovieListJson(String jsonRoot) {
        List<Movie> movies = new ArrayList<>();

        try {
            JSONObject jsonMovies = new JSONObject(jsonRoot);
            JSONArray jsonArray = jsonMovies.getJSONArray(MOVIE_RESULTS);

            for (int i = 0; i < jsonArray.length(); i++) {
                movies.add(parseSingleMovieJson((JSONObject) jsonArray.get(i)));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movies;
    }

    private static Movie parseSingleMovieJson(JSONObject jsonElement) throws JSONException {

        return new Movie(
                jsonElement.getString(MOVIE_TITLE),
                MOVIES_DB_BASE_THUMBNAIL_URL + jsonElement.getString(MOVIE_PATH),
                jsonElement.getString(MOVIE_OVERVIEW),
                jsonElement.getString(MOVIE_AVERAGE),
                jsonElement.getString(MOVIE_RELEASE_DATE));
    }
}
