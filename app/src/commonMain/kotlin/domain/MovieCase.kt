package domain

import data.api.MovieApi
import data.model.MovieContainer
import data.repo.MovieRepo

class MovieCase(private val movieRepo: MovieRepo) {
    suspend fun getMovie(): MovieContainer {
        return movieRepo.getMovies()
    }
}

object CaseProvider {
    fun getCase() = MovieCase(MovieRepo(MovieApi()))
}