package io.nanodegree.andrea.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.nanodegree.andrea.popularmovies.model.Movie;

/**
 * Created by Andrea Loddo (andrea@evenly.io) on 11.05.2018
 * for Evenly GmbH
 * <p>
 * Copyright (c) 2018 Evenly GmbH,
 * all rights reserved
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieItemViewHolder> {

    private List<Movie> movies;
    private Context context;

    MoviesAdapter(@NonNull Context context, @NonNull List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.movie_item, parent, false);

        return new MovieItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieItemViewHolder holder, int position) {
        holder.bind(movies.get(position).getImageThumbnailUrl());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView moviePoster;

        MovieItemViewHolder(View itemView) {
            super(itemView);

            moviePoster = itemView.findViewById(R.id.movie_poster);
            itemView.setOnClickListener(this);
        }

        void bind(String imageUrl) {
            Picasso.get().load("http:/image.tmdb.org/t/p/w500" + imageUrl).into(moviePoster);
        }

        @Override
        public void onClick(View v) {
            context.startActivity(new Intent(context, DetailActivity.class));
        }
    }
}
