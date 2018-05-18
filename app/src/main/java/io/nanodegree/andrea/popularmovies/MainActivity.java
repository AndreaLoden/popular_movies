package io.nanodegree.andrea.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.nanodegree.andrea.popularmovies.api.GetMoviesTask;
import io.nanodegree.andrea.popularmovies.api.NetworkUtils;
import io.nanodegree.andrea.popularmovies.model.Movie;

public class MainActivity extends AppCompatActivity implements GetMoviesTask.MoviesResults {

    @BindView(R.id.recycler_view_movies)
    RecyclerView recyclerView;

    @BindView(R.id.errorView)
    TextView errorView;

    @BindView(R.id.progress_bar)
    View progressBar;

    @BindView(R.id.my_toolbar)
    Toolbar toolbar;

    MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        moviesAdapter = new MoviesAdapter(this);

        int spanCount = getSpanCount();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, spanCount);

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.movie_item_margin);
        recyclerView.addItemDecoration(new SpacesItemDecoration(spanCount, spacingInPixels));
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(moviesAdapter);

        getTopRatedMoviesTask();
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
        new GetMoviesTask(this).execute(NetworkUtils.buildPopularMoviesUrl());
    }

    private void getTopRatedMoviesTask() {
        new GetMoviesTask(this).execute(NetworkUtils.buildTopRatedMoviesUrl());
    }

    //Internal methods

    private int getSpanCount() {
        return getResources().getInteger(R.integer.span_count);
    }

    //Handle visibility of views
    private void showContent() {
        recyclerView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }

    private void showNoItems() {
        recyclerView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
        errorView.setText(R.string.no_results);
        progressBar.setVisibility(View.GONE);
    }

    private void showError() {
        recyclerView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }
}
