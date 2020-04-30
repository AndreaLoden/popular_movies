package io.popularmovies.kmmp

class PopularListMovieRepoImpl(private val api: MovieApi) :
    PopularListMovieRepo {
    override suspend fun getPopularMovies(): Either<ApiError, List<MovieContainer.Movie>> = api.getPopularMovies()
}