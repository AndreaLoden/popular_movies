package data.repo

import data.api.MovieApi
import data.model.MovieContainer

class MovieRepo(private val api: MovieApi) {
    suspend fun getMovies(): MovieContainer {
        return api.getMovies()
    }
}