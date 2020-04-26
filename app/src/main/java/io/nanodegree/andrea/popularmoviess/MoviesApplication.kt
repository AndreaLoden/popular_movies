package io.nanodegree.andrea.popularmoviess

import android.app.Application
import com.jetbrains.handson.mpp.mobile.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.*

class MoviesApplication : Application(), KodeinAware {

    override val kodein by Kodein.lazy {
        import(androidXModule(this@MoviesApplication))

        bind<MovieApi>() with singleton { MovieApi() }

        bind<MovieReviewRepo>() with singleton { MovieReviewRepoImpl(instance()) }
        bind<PopularListMovieRepo>() with singleton { PopularListMovieRepoImpl(instance()) }

        bind<MovieListCase>() with singleton { MovieListCase(instance()) }
        bind<MovieDetailCase>() with singleton { MovieDetailCase(instance()) }
    }
}
