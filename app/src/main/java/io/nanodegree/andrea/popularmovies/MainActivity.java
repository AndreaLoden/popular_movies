package io.nanodegree.andrea.popularmovies;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import io.nanodegree.andrea.popularmovies.api.GetMoviesTask;
import io.nanodegree.andrea.popularmovies.api.NetworkUtils;
import io.nanodegree.andrea.popularmovies.databinding.ActivityMainBinding;
import io.nanodegree.andrea.popularmovies.model.Movie;

public class MainActivity extends AppCompatActivity implements GetMoviesTask.MoviesResults {

    ActivityMainBinding binding;

    MoviesAdapter moviesAdapter;

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

        getPopularMoviesTask();
    }

    @Override
    public void onMoviesReady(List<Movie> movies) {
        if (movies.size() > 0) {
            moviesAdapter.setData(movies);
            moviesAdapter.notifyDataSetChanged();
            showContent();
        } else {
            showNoItems();
        }
    }

    @Override
    public void onError() {
        showError();
    }

    private void getPopularMoviesTask() {

        getSupportActionBar().setTitle("Popular movies");
        new GetMoviesTask(this).execute(NetworkUtils.buildPopularMoviesUrl());
    }

    private void getTopRatedMoviesTask() {
        getSupportActionBar().setTitle("Top rated movies");
        new GetMoviesTask(this).execute(NetworkUtils.buildTopRatedMoviesUrl());
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
                getPopularMoviesTask();
                return true;
            case R.id.top_rated_movies:
                getTopRatedMoviesTask();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
