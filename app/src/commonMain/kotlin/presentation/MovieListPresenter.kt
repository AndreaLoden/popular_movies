package presentation

import data.api.Background
import data.api.Main
import domain.CaseProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListPresenter(private val movieListView: MovieListView) {

    private val case = CaseProvider.getMovieListCase()

    fun start() {
        GlobalScope.apply {
            launch(Background) {
                val s = case.getPopularMoviesRepo()
                println(s)
                withContext(Main) {
                    movieListView.showState(MovieState(s))
                }
            }
        }
    }
}