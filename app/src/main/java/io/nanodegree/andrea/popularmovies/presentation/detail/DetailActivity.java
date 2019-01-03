package io.nanodegree.andrea.popularmovies.presentation.detail;

import android.os.Bundle;
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
import io.nanodegree.andrea.popularmovies.persistence.MovieDatabase;
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
    MovieDatabase movieDatabase;

    private TrailersAdapter trailersAdapter;
    private ReviewsAdapter reviewsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.detail_activity);
        movieDatabase = MovieDatabase.getInstance(getApplicationContext());
        showLoading();

        setSupportActionBar(binding.myToolbar);

        if (getIntent() != null && getIntent().hasExtra(MOVIE_EXTRA)) {
            showContent();
            final Movie movie = (Movie) getIntent().getSerializableExtra(MOVIE_EXTRA);

            getSupportActionBar().setTitle(movie.originalTitle);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            Picasso.get().load(movie.getFormattedImageThumbnailUrl()).into(binding.contentLayout.imageIv);
            binding.contentLayout.tvPlotContent.setText(movie.plotSynopsis);
            binding.contentLayout.tvReleaseDateContent.setText(movie.releaseDate);
            binding.contentLayout.tvRatingContent.setText(getString(R.string.detail_rating_out_of, movie.userRating));

            trailersAdapter = new TrailersAdapter(this);
            binding.contentLayout.tvTrailersList.setAdapter(trailersAdapter);
            binding.contentLayout.tvTrailersList.setLayoutManager(new LinearLayoutManager(this));
            Call<VideoContainer> movieVideosCall = MovieDbClient.getPopularMoviesService().getMovieVideos(movie.id);
            movieVideosCall.enqueue(videoContainerCallback);


            reviewsAdapter = new ReviewsAdapter(this);
            binding.contentLayout.tvReviewsList.setAdapter(reviewsAdapter);
            binding.contentLayout.tvReviewsList.setLayoutManager(new LinearLayoutManager(this));
            Call<ReviewsContainer> reviewsContainerCall = MovieDbClient.getPopularMoviesService().getMovieReviews(movie.id);
            reviewsContainerCall.enqueue(reviewsContainerCallback);

            if (movieDatabase.getMovieDao().getMovie(movie.id) != null) {
                binding.contentLayout.imageIv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        movieDatabase.getMovieDao().deleteMovie(movie);
                    }
                });
            } else {
                binding.contentLayout.imageIv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        movieDatabase.getMovieDao().insertMovie(movie);
                    }
                });
            }

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

    private void onSaveMovieInFavorites() {

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
                    binding.contentLayout.tvTrailersLabel.setVisibility(View.GONE);
                }
            } else {
                binding.contentLayout.tvTrailersLabel.setVisibility(View.GONE);
            }
        }

        @Override
        public void onFailure(Call<VideoContainer> call, Throwable t) {
            binding.contentLayout.tvTrailersLabel.setVisibility(View.GONE);
        }
    };

    Callback<ReviewsContainer> reviewsContainerCallback = new Callback<ReviewsContainer>() {
        @Override
        public void onResponse(Call<ReviewsContainer> call, Response<ReviewsContainer> response) {

            if (response.isSuccessful() && response.body() != null) {

                List<Review> reviewList = response.body().reviewList;

                if (reviewList != null && reviewList.size() > 0) {
                    reviewsAdapter.setData(reviewList);
                    reviewsAdapter.notifyDataSetChanged();
                } else {
                    binding.contentLayout.tvReviewsLabel.setVisibility(View.GONE);
                }
            } else {
                binding.contentLayout.tvReviewsLabel.setVisibility(View.GONE);
            }
        }

        @Override
        public void onFailure(Call<ReviewsContainer> call, Throwable t) {
            binding.contentLayout.tvReviewsLabel.setVisibility(View.GONE);
        }
    };
}
