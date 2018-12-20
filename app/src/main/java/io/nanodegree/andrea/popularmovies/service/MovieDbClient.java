package io.nanodegree.andrea.popularmovies.service;

import java.io.IOException;

import androidx.annotation.NonNull;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by Andrea Loddo (andrea@evenly.io) on 20.12.2018
 * for Evenly GmbH
 * <p>
 * Copyright (c) 2018 Evenly GmbH,
 * all rights reserved
 */
public class MovieDbClient {

    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/";
    private static final String API_KEY = "";
    private static PopularMoviesService service;

    private static void initPopularMoviesService() {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        HttpUrl url = request.url().newBuilder().addQueryParameter("api_key", API_KEY).build();
                        request = request.newBuilder().url(url).build();
                        return chain.proceed(request);
                    }
                }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        service = retrofit.create(PopularMoviesService.class);
    }

    @NonNull
    public static PopularMoviesService getPopularMoviesService() {

        if (service == null) {
            initPopularMoviesService();
        }

        return service;
    }
}
