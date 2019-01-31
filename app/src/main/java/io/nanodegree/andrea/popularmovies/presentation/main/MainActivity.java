package io.nanodegree.andrea.popularmovies.presentation.main;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import io.nanodegree.andrea.popularmovies.R;
import io.nanodegree.andrea.popularmovies.databinding.ActivityMainBinding;
import io.nanodegree.andrea.popularmovies.model.Movie;
import io.nanodegree.andrea.popularmovies.model.MovieContainer;
import io.nanodegree.andrea.popularmovies.service.MovieDbClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<MovieContainer> {

    private static final String BUNDLE_RECYCLER_LAYOUT = "mainactivity.recycler.layout";
    private static final String BUNDLE_MOVIES = "mainactivity.data.movies";

    private ActivityMainBinding binding;
    private MoviesAdapter moviesAdapter;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(binding.myToolbar);

        moviesAdapter = new MoviesAdapter(this);

        setupRecyclerView();

        if (savedInstanceState == null) {
            //It means it is a fresh start in the activity. If savedInstanceState is not null we will
            //restore state in the onRestoreInstanceState method

            Call<MovieContainer> popularMoviesCall = MovieDbClient.getPopularMoviesService().getListPopularMovies();
            popularMoviesCall.enqueue(this);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(BUNDLE_RECYCLER_LAYOUT, gridLayoutManager.onSaveInstanceState());
        outState.putSerializable(BUNDLE_MOVIES, moviesAdapter.getData());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        try {
            List<Movie> movies = (ArrayList<Movie>) savedInstanceState.getSerializable(BUNDLE_MOVIES);
            moviesAdapter.setData(movies);
            moviesAdapter.notifyDataSetChanged();
            showContent();

            Parcelable state = savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT);
            gridLayoutManager.onRestoreInstanceState(state);

        } catch (ClassCastException cce) {
            Log.d(MainActivity.class.getName(), "Unable to deserialize passed movies");
        }

        super.onRestoreInstanceState(savedInstanceState);
    }

    private void setupRecyclerView() {
        int spanCount = getSpanCount();
        gridLayoutManager = new GridLayoutManager(this, spanCount);

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.movie_item_margin);

        binding.recyclerViewMovies.addItemDecoration(new SpacesItemDecoration(spanCount, spacingInPixels));
        binding.recyclerViewMovies.setLayoutManager(gridLayoutManager);
        binding.recyclerViewMovies.setHasFixedSize(true);
        binding.recyclerViewMovies.setAdapter(moviesAdapter);
    }

    private void getPopularMovies() {

        Call<MovieContainer> popularMoviesCall = MovieDbClient.getPopularMoviesService().getListPopularMovies();
        popularMoviesCall.enqueue(this);
    }

    private void getTopRatedMovies() {

        Call<MovieContainer> topRatedMoviesCall = MovieDbClient.getPopularMoviesService().getListTopRatedMovies();
        topRatedMoviesCall.enqueue(this);
    }

    private void getFavoriteMovies() {

        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        viewModel.getMovieList().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                if (movies != null && movies.size() > 0) {
                    showResults(movies);
                } else {
                    showNoItems();
                }
            }
        });
    }

    private void showResults(List<Movie> movies){
        moviesAdapter.setData(movies);
        moviesAdapter.notifyDataSetChanged();
        showContent();

        binding.recyclerViewMovies.smoothScrollToPosition(0);
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
            case R.id.favorite_movies:
                getFavoriteMovies();
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
                showResults(movies);
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
