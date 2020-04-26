package com.jetbrains.handson.mpp.mobile

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListPresenter(private val movieListView: MovieListView, private val movieListCase: MovieListCase) {

    fun start() {
        GlobalScope.apply {
            launch(Background) {
                val s = movieListCase.getPopularMoviesRepo()
                println(s)
                withContext(Main) {
                    movieListView.showState(
                        MovieState(
                            s
                        )
                    )
                }
            }
        }
    }
}