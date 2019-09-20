package io.nanodegree.andrea.popularmovies

import io.nanodegree.andrea.popularmovies.data.repository.MovieDetailRepositoryImpl
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import io.nanodegree.andrea.popularmovies.data.repository.MovieListRepositoryImpl
import io.nanodegree.andrea.popularmovies.data.retrofit.MovieDbClient
import io.nanodegree.andrea.popularmovies.domain.repository.MovieDetailRepository
import io.nanodegree.andrea.popularmovies.domain.repository.MovieListRepository
import io.nanodegree.andrea.popularmovies.domain.usecase.GetMovieDetailUseCase
import io.nanodegree.andrea.popularmovies.domain.usecase.GetMovieListUseCase
import io.nanodegree.andrea.popularmovies.presentation.moviedetail.MovieDetailViewModel
import io.nanodegree.andrea.popularmovies.presentation.movielist.MovieListViewModel

val movieModule = module {

    single { MovieDbClient.popularMoviesService }

    single<MovieListRepository> { MovieListRepositoryImpl(get()) }
    single { GetMovieListUseCase(get()) }
    viewModel { MovieListViewModel(get()) }

    single<MovieDetailRepository> { MovieDetailRepositoryImpl(get()) }
    single { GetMovieDetailUseCase(get()) }
    viewModel { MovieDetailViewModel(get()) }
}