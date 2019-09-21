import com.nhaarman.mockitokotlin2.given
import io.nanodegree.andrea.popularmovies.DataFixtures
import io.nanodegree.andrea.popularmovies.data.repository.MovieListRepositoryImpl
import io.nanodegree.andrea.popularmovies.data.retrofit.MoviesService
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryImplTest {

    @Mock
    internal lateinit var mockService: MoviesService

    private lateinit var movieListRepositoryImpl: MovieListRepositoryImpl

    @Before
    fun setUp() {
        movieListRepositoryImpl = MovieListRepositoryImpl(mockService)
    }

    @Test
    fun `listPopularMovies fetches MovieContainer`() {
        runBlocking {
            // given
            given(mockService.listPopularMovies()).willReturn(DataFixtures.getPopulatedMovieContainer())

            // when
            val result = movieListRepositoryImpl.getPopularMovies()

            // then
            result shouldEqual DataFixtures.getPopulatedMovieContainer()
        }
    }
}