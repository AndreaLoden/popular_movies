package io.nanodegree.andrea.popularmovies.presentation.detail;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import io.nanodegree.andrea.popularmovies.R;
import io.nanodegree.andrea.popularmovies.databinding.DetailActivityBinding;
import io.nanodegree.andrea.popularmovies.model.Movie;
import io.nanodegree.andrea.popularmovies.model.Review;
import io.nanodegree.andrea.popularmovies.model.ReviewsContainer;
import io.nanodegree.andrea.popularmovies.model.Video;
import io.nanodegree.andrea.popularmovies.model.VideoContainer;
import io.nanodegree.andrea.popularmovies.service.MovieDbClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Andrea Loddo (andrea@evenly.io) on 11.05.2018
 * for Evenly GmbH
 * <p>
 * Copyright (c) 2018 Evenly GmbH,
 * all rights reserved
 */
public class DetailActivity extends AppCompatActivity {

    public final static String MOVIE_EXTRA = "extra.movie";

    DetailActivityBinding binding;

    private TrailersAdapter trailersAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.detail_activity);
        showLoading();

        setSupportActionBar(binding.myToolbar);

        if (getIntent() != null && getIntent().hasExtra(MOVIE_EXTRA)) {
            showContent();
            Movie movie = (Movie) getIntent().getSerializableExtra(MOVIE_EXTRA);

            getSupportActionBar().setTitle(movie.originalTitle);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            Picasso.get().load(movie.getImageThumbnailUrl()).into(binding.contentLayout.imageIv);
            binding.contentLayout.tvPlotContent.setText(movie.plotSynopsis);
            binding.contentLayout.tvReleaseDateContent.setText(movie.releaseDate);
            binding.contentLayout.tvRatingContent.setText(getString(R.string.detail_rating_out_of, movie.userRating));

            trailersAdapter = new TrailersAdapter(this);
            binding.contentLayout.tvTrailersList.setAdapter(trailersAdapter);
            binding.contentLayout.tvTrailersList.setLayoutManager(new LinearLayoutManager(this));
            Call<VideoContainer> movieVideosCall = MovieDbClient.getPopularMoviesService().getMovieVideos(movie.id);
            movieVideosCall.enqueue(videoContainerCallback);

            Call<ReviewsContainer> reviewsContainerCall = MovieDbClient.getPopularMoviesService().getMovieReviews(movie.id);
            reviewsContainerCall.enqueue(reviewsContainerCallback);
        } else {
            showError();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Press Back Icon
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    //Handle visibility of views
    private void showContent() {
        binding.contentLayout.content.setVisibility(View.VISIBLE);
        binding.errorView.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.GONE);
    }

    private void showError() {
        binding.contentLayout.content.setVisibility(View.GONE);
        binding.errorView.setVisibility(View.VISIBLE);
        binding.progressBar.setVisibility(View.GONE);
    }

    private void showLoading() {
        binding.contentLayout.content.setVisibility(View.GONE);
        binding.errorView.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    Callback<VideoContainer> videoContainerCallback = new Callback<VideoContainer>() {
        @Override
        public void onResponse(Call<VideoContainer> call, Response<VideoContainer> response) {

            if (response.isSuccessful() && response.body() != null) {

                List<Video> videoList = response.body().getYoutubeTrailers();

                if (videoList != null && videoList.size() > 0) {
                    trailersAdapter.setData(videoList);
                    trailersAdapter.notifyDataSetChanged();
                } else {
                    // empty adapter
                }
            } else {
                // empty adapter
            }
        }

        @Override
        public void onFailure(Call<VideoContainer> call, Throwable t) {

        }
    };

    Callback<ReviewsContainer> reviewsContainerCallback = new Callback<ReviewsContainer>() {
        @Override
        public void onResponse(Call<ReviewsContainer> call, Response<ReviewsContainer> response) {

            for (Review review : response.body().reviewList) {
                Log.d("DETAIL", "Author: " + review.author);
            }
        }

        @Override
        public void onFailure(Call<ReviewsContainer> call, Throwable t) {

        }
    };
}
