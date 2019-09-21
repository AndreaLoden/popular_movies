package io.nanodegree.andrea.popularmovies.domain.usecase

import com.nhaarman.mockitokotlin2.given
import io.nanodegree.andrea.popularmovies.DataFixtures
import io.nanodegree.andrea.popularmovies.data.repository.MovieListRepositoryImpl
import io.nanodegree.andrea.popularmovies.data.retrofit.response.MovieContainer
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetMovieListUseCaseTest {

    @Mock
    internal lateinit var mockMovieListRepository: MovieListRepositoryImpl

    private lateinit var getMovieListUseCase: GetMovieListUseCase

    @Before
    fun setUp() {
        getMovieListUseCase = GetMovieListUseCase(mockMovieListRepository)
    }

    @Test
    fun `returns a MovieContainer`() {
        runBlocking {
            // given
            val movieContainer = DataFixtures.getPopulatedMovieContainer()
            given(mockMovieListRepository.getPopularMovies()).willReturn(movieContainer)

            // when
            val result = getMovieListUseCase.getMoviesList()

            // then
            result shouldEqual movieContainer.movieList
        }
    }

    @Test
    fun `filter out movies with empty image`() {
        runBlocking {
            // given
            val movieWithImage = DataFixtures.getMovie()
            val movieWithoutImage = DataFixtures.getMovie(imageThumbnailUrl = "")
            val movies = listOf(movieWithImage, movieWithoutImage)
            val movieContainer = MovieContainer(movies)

            given(mockMovieListRepository.getPopularMovies()).willReturn(movieContainer)

            // when
            val result = getMovieListUseCase.getMoviesList()

            // then
            result shouldEqual listOf(movieWithImage)
        }
    }

    @Test
    fun `filter out movies without image`() {
        runBlocking {
            // given
            val movieWithImage = DataFixtures.getMovie()
            val movieWithoutImage = DataFixtures.getMovie(imageThumbnailUrl = null)
            val movies = listOf(movieWithImage, movieWithoutImage)
            val movieContainer = MovieContainer(movies)

            given(mockMovieListRepository.getPopularMovies()).willReturn(movieContainer)

            // when
            val result = getMovieListUseCase.getMoviesList()

            // then
            result shouldEqual listOf(movieWithImage)
        }
    }
}
