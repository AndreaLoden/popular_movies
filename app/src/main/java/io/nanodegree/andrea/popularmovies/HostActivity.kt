package io.nanodegree.andrea.popularmovies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.nanodegree.andrea.popularmovies.service.MovieDbClient
import kotlinx.android.synthetic.main.activity_host.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.nanodegree.popularmovies.feature.movie.data.retrofit.response.MovieContainer

class HostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)
        setSupportActionBar(toolbar)

        val popularMoviesCall = MovieDbClient.getPopularMoviesService().listPopularMovies
        popularMoviesCall.enqueue(object : Callback<MovieContainer> {
            override fun onFailure(call: Call<MovieContainer>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<MovieContainer>, response: Response<MovieContainer>) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })

    }

}
