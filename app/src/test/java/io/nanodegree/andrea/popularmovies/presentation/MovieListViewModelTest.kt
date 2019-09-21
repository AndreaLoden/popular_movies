package io.nanodegree.andrea.popularmovies.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.verify
import io.nanodegree.andrea.popularmovies.CoroutineRule
import io.nanodegree.andrea.popularmovies.DataFixtures
import io.nanodegree.andrea.popularmovies.domain.usecase.GetMovieListUseCase
import io.nanodegree.andrea.popularmovies.presentation.movielist.MovieListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieListViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    internal lateinit var mockGetMovieUseCaseTest: GetMovieListUseCase

    private lateinit var movieListViewModel: MovieListViewModel

    @Before
    fun setUp() {
        movieListViewModel = MovieListViewModel(mockGetMovieUseCaseTest)
    }

    /**
     * Throws a NullPointerException but the test is successful anyway because GetMovieListUseCase
     * is executed
     */
    @Test
    fun `execute getMovieUseCase`() {
        // when
        movieListViewModel.loadMovies()

        // then
        runBlocking {
            verify(mockGetMovieUseCaseTest).getMoviesList()
        }
    }

    @Test
    fun `verify state when GetMovieListUseCase returns empty list`() {
        // given
        mockGetMovieUseCaseTest.stub {
            onBlocking { getMoviesList() } doReturn listOf()
        }
        // when
        movieListViewModel.loadMovies()

        // then
        movieListViewModel.stateLiveData.value shouldEqual MovieListViewModel.ViewState(
                isLoading = false,
                isError = true,
                movies = listOf()
        )
    }

    @Test
    fun `verify state when GetMovieListUseCase returns non-empty list`() {
        // given
        val movies = listOf(DataFixtures.getMovie())
        mockGetMovieUseCaseTest.stub {
            onBlocking { getMoviesList() } doReturn movies
        }

        // when
        movieListViewModel.loadMovies()

        // then
        movieListViewModel.stateLiveData.value shouldEqual MovieListViewModel.ViewState(
                isLoading = false,
                isError = false,
                movies = movies
        )
    }
}
