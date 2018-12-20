package io.nanodegree.andrea.popularmovies;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.io.IOException;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import io.nanodegree.andrea.popularmovies.databinding.ActivityMainBinding;
import io.nanodegree.andrea.popularmovies.model.Movie;
import io.nanodegree.andrea.popularmovies.model.MovieContainer;
import io.nanodegree.andrea.popularmovies.service.PopularMoviesService;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<MovieContainer> {

    ActivityMainBinding binding;

    MoviesAdapter moviesAdapter;

    Retrofit retrofit;
    PopularMoviesService popularMoviesService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(binding.myToolbar);

        moviesAdapter = new MoviesAdapter(this);

        int spanCount = getSpanCount();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, spanCount);

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.movie_item_margin);
        binding.recyclerViewMovies.addItemDecoration(new SpacesItemDecoration(spanCount, spacingInPixels));
        binding.recyclerViewMovies.setLayoutManager(gridLayoutManager);
        binding.recyclerViewMovies.setHasFixedSize(true);
        binding.recyclerViewMovies.setAdapter(moviesAdapter);


        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        HttpUrl url = request.url().newBuilder().addQueryParameter("api_key", "").build();
                        request = request.newBuilder().url(url).build();
                        return chain.proceed(request);
                    }
                }).build();

        // Trailing slash is needed
        final String BASE_URL = "https://api.themoviedb.org/3/movie/";
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        popularMoviesService = retrofit.create(PopularMoviesService.class);

        Call<MovieContainer> popularMoviesCall = popularMoviesService.listPopularMovies();
        popularMoviesCall.enqueue(this);
    }


    private void getPopularMovies() {
        Call<MovieContainer> popularMoviesCall = popularMoviesService.listPopularMovies();
        popularMoviesCall.enqueue(this);
    }

    private void getTopRatedMovies() {
        Call<MovieContainer> topRatedMoviesCall = popularMoviesService.listTopRatedMovies();
        topRatedMoviesCall.enqueue(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.popular_movie:
                getPopularMovies();
                return true;
            case R.id.top_rated_movies:
                getTopRatedMovies();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //Implementation of the callback methods for the network call
    @Override
    public void onResponse(Call<MovieContainer> call, Response<MovieContainer> response) {
        if (response.isSuccessful() && response.body() != null) {
            List<Movie> movies = response.body().movieList;

            if (movies != null && movies.size() > 0) {
                moviesAdapter.setData(movies);
                moviesAdapter.notifyDataSetChanged();
                showContent();
            } else {
                showNoItems();
            }
        } else {
            showError();
        }
    }

    @Override
    public void onFailure(Call<MovieContainer> call, Throwable t) {
        showError();
    }

    //Internal methods

    private int getSpanCount() {
        return getResources().getInteger(R.integer.span_count);
    }

    //Handle visibility of views
    private void showContent() {
        binding.recyclerViewMovies.setVisibility(View.VISIBLE);
        binding.errorView.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.GONE);
    }

    private void showNoItems() {
        binding.recyclerViewMovies.setVisibility(View.GONE);
        binding.errorView.setVisibility(View.VISIBLE);
        binding.errorView.setText(R.string.no_results);
        binding.progressBar.setVisibility(View.GONE);
    }

    private void showError() {
        binding.recyclerViewMovies.setVisibility(View.GONE);
        binding.errorView.setVisibility(View.VISIBLE);
        binding.progressBar.setVisibility(View.GONE);
    }
}
