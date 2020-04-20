package presentation

import data.api.Background
import data.api.Main
import domain.CaseProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviePresenter(private val view: MovieView) {
    private val case = CaseProvider.getCase()

    fun start() {
        GlobalScope.apply {
            launch(Background) {
                val s = case.getMovie()
                println(s)
                withContext(Main) {
                    view.showState(MovieState(s))
                }
            }
        }
    }
}