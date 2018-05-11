package io.nanodegree.andrea.popularmovies.api;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import io.nanodegree.andrea.popularmovies.model.Movie;

/**
 * Created by Andrea Loddo (andrea@evenly.io) on 11.05.2018
 * for Evenly GmbH
 * <p>
 * Copyright (c) 2018 Evenly GmbH,
 * all rights reserved
 */
public class GetMoviesTask extends AsyncTask<URL, Void, String> {

    public interface MoviesResults {
        void onMoviesReady(List<Movie> movies);
    }

    private MoviesResults moviesResultsNotifier;

    public GetMoviesTask(MoviesResults moviesResultsNotifier) {
        this.moviesResultsNotifier = moviesResultsNotifier;
    }

    @Override
    protected String doInBackground(URL[] urls) {
        URL searchUrl = urls[0];
        String movieDbResults = null;
        try {
            movieDbResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movieDbResults;
    }

    @Override
    protected void onPostExecute(String string) {
        if (string != null && !string.isEmpty()) {
            List<Movie> movies = JsonUtils.parseMovieListJson(string);
            if (moviesResultsNotifier != null) {
                moviesResultsNotifier.onMoviesReady(movies);
            }
        }
    }
}
