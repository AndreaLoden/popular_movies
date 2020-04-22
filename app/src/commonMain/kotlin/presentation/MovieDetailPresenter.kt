package presentation

import data.api.Background
import data.api.Main
import domain.CaseProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailPresenter(private val movieDetailView: MovieDetailView) {

    private val case = CaseProvider.getMovieDetailCase()

    fun start(id: String) {

        GlobalScope.apply {
            launch(Background) {

                val reviews = async { case.getMovieReviewsRepo(id) }
                val trailers = async { case.getMovieTrailersRepo(id) }

                withContext(Main) {
                    movieDetailView.showState(MovieDetailState(reviews.await(), trailers.await()))
                }
            }
        }
    }
}