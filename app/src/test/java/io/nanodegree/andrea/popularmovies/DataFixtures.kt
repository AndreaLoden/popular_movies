package io.nanodegree.andrea.popularmovies

import io.nanodegree.andrea.popularmovies.data.model.Movie
import io.nanodegree.andrea.popularmovies.data.retrofit.response.MovieContainer

object DataFixtures {

    internal fun getMovie(id: String? = "id",
                          originalTitle: String? = "title",
                          plotSynopsis: String? = "Something happens",
                          userRating: String? = "10",
                          releaseDate: String? = "2019-01-01",
                          imageThumbnailUrl: String? = "someurl"): Movie {
        return Movie(
                id,
                originalTitle,
                plotSynopsis,
                userRating,
                releaseDate,
                imageThumbnailUrl
        )
    }

    internal fun getPopulatedMovieContainer(movies: MutableList<Movie> = mutableListOf()): MovieContainer {
        movies.add(getMovie())
        return MovieContainer(movies)
    }

}