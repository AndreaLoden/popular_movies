package io.nanodegree.andrea.popularmovies;

import android.os.Bundle;
import android.view.MenuItem;

import com.squareup.picasso.Picasso;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import io.nanodegree.andrea.popularmovies.databinding.DetailActivityBinding;
import io.nanodegree.andrea.popularmovies.model.Movie;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.detail_activity);

        setSupportActionBar(binding.myToolbar);

        if (getIntent() != null && getIntent().hasExtra(MOVIE_EXTRA)) {
            Movie movie = (Movie) getIntent().getSerializableExtra(MOVIE_EXTRA);

            getSupportActionBar().setTitle(movie.getOriginalTitle());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            Picasso.get().load(movie.getImageThumbnailUrl()).into(binding.imageIv);
            binding.tvPlotContent.setText(movie.getPlotSynopsis());
            binding.tvReleaseDateContent.setText(movie.getReleaseDate());
            binding.tvRatingContent.setText(getString(R.string.rating_out_of, movie.getUserRating()));
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
}
