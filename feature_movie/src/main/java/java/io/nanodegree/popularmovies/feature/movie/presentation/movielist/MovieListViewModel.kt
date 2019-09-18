package java.io.nanodegree.popularmovies.feature.movie.presentation.movielist

import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.nanodegree.popularmovies.feature.movie.data.retrofit.response.MovieContainer
import java.io.nanodegree.popularmovies.feature.movie.domain.usecase.GetPopularMoviesListUseCase

internal class MovieListViewModel(private val getPopularMoviesListUseCase: GetPopularMoviesListUseCase) : ViewModel() {

    fun loadMovies() {
        /*viewModelScope.launch {
            getPopularMoviesListUseCase.execute()
                    .also {
                        Log.d("diomerda","diomerda")
                    }
        }*/

    }
}