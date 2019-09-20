package io.nanodegree.andrea.popularmovies.data.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Andrea Loddo (andrea@evenly.io) on 20.12.2018
 * for Evenly GmbH
 *
 *
 * Copyright (c) 2018 Evenly GmbH,
 * all rights reserved
 */
object MovieDbClient {

    private const val BASE_URL = "https://api.themoviedb.org/3/movie/"
    private const val API_KEY = "220e2ce24c38e16c4eafe5708e0e39d4"

    val popularMoviesService: MoviesService by lazy {

        val client = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    var request = chain.request()
                    val url = request.url().newBuilder().addQueryParameter("api_key", API_KEY).build()
                    request = request.newBuilder().url(url).build()
                    chain.proceed(request)
                }.build()

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return@lazy retrofit.create(MoviesService::class.java)
    }
}
