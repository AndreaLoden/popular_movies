package io.nanodegree.andrea.popularmovies.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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


    @NonNull
    public static List<Movie> parseMovieListJson(String jsonRoot) {
        List<Movie> movies = new ArrayList<>();

        try {
            JSONObject jsonMovies = new JSONObject(jsonRoot);
            JSONArray jsonArray = jsonMovies.getJSONArray("results");

            for (int i = 0; i < jsonArray.length(); i++) {
                movies.add(parseSingleMovieJson((JSONObject) jsonArray.get(i)));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movies;
    }

    @Nullable
    private static Movie parseSingleMovieJson(JSONObject jsonElement) throws JSONException {

        return new Movie(
                jsonElement.getString("title"),
                jsonElement.getString("poster_path"),
                jsonElement.getString("overview"),
                jsonElement.getString("vote_average"),
                jsonElement.getString("release_date"));
    }
}
