package presentation

import data.model.MovieContainer

data class MovieState(val movieContainer: MovieContainer)

interface MovieView {
    fun showState(state: MovieState)
}