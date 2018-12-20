package io.nanodegree.andrea.popularmovies.api;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Andrea Loddo (andrea@evenly.io) on 11.05.2018
 * for Evenly GmbH
 * <p>
 * Copyright (c) 2018 Evenly GmbH,
 * all rights reserved
 * <p>
 * These utilities will be used to communicate with the network.
 */
public class NetworkUtils {

    private final static String MOVIES_DB_BASE_URL = "https://api.themoviedb.org/3/movie/";
    private final static String POPULAR_MOVIES_SUFFIX = "popular";
    private final static String TOP_RATED_MOVIES_SUFFIX = "top_rated";
    private final static String API_KEY_FIELD = "api_key";

    //TODO add here a valid token to query the Movie DB
    private final static String TOKEN = "220e2ce24c38e16c4eafe5708e0e39d4";

    /**
     * Builds the URL used to query the Movie DB for popular movies.
     *
     * @return The URL to use to query the MovieDB.
     */
    public static URL buildPopularMoviesUrl() {
        return buildUrl(POPULAR_MOVIES_SUFFIX);
    }

    /**
     * Builds the URL used to query the Movie DB for top rated movies.
     *
     * @return The URL to use to query the MovieDB.
     */
    public static URL buildTopRatedMoviesUrl() {
        return buildUrl(TOP_RATED_MOVIES_SUFFIX);
    }

    private static URL buildUrl(String requestSuffix) {
        Uri builtUri = Uri.parse(MOVIES_DB_BASE_URL)
                .buildUpon()
                .appendPath(requestSuffix)
                .appendQueryParameter(API_KEY_FIELD, TOKEN)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
