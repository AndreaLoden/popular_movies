package java.io.nanodegree.popularmovies.feature.movie

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.io.nanodegree.popularmovies.feature.movie.data.repository.MovieListRepositoryImpl
import java.io.nanodegree.popularmovies.feature.movie.data.retrofit.MovieDbClient
import java.io.nanodegree.popularmovies.feature.movie.domain.repository.MovieListRepository
import java.io.nanodegree.popularmovies.feature.movie.domain.usecase.GetMovieListUseCase
import java.io.nanodegree.popularmovies.feature.movie.presentation.moviedetail.MovieDetailViewModel
import java.io.nanodegree.popularmovies.feature.movie.presentation.movielist.MovieListViewModel

val movieModule = module {

    single { MovieDbClient.popularMoviesService }

    single<MovieListRepository> { MovieListRepositoryImpl(get()) }

    single { GetMovieListUseCase(get()) }

    viewModel { MovieListViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }
}