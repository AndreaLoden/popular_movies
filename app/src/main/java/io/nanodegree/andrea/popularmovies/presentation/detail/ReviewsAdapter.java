package io.nanodegree.andrea.popularmovies.presentation.detail;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.nanodegree.andrea.popularmovies.R;
import io.nanodegree.andrea.popularmovies.model.Review;

/**
 * Created by Andrea Loddo (andrea@evenly.io) on 11.05.2018
 * for Evenly GmbH
 * <p>
 * Copyright (c) 2018 Evenly GmbH,
 * all rights reserved
 */
public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewItemViewHolder> {

    private List<Review> reviewList;
    private Context context;

    ReviewsAdapter(@NonNull Context context) {
        this.context = context;
        reviewList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ReviewItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.review_item, parent, false);

        return new ReviewItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewItemViewHolder holder, int position) {
        holder.bind(reviewList.get(position));
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public void setData(List<Review> reviews) {
        this.reviewList.clear();
        this.reviewList.addAll(reviews);
    }

    class ReviewItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_review_title;
        TextView tv_review_content;
        Review review;

        ReviewItemViewHolder(View itemView) {
            super(itemView);

            tv_review_title = itemView.findViewById(R.id.review_title);
            tv_review_content = itemView.findViewById(R.id.review_content);
            itemView.setOnClickListener(this);
        }

        void bind(Review review) {
            this.review = review;
            tv_review_title.setText(review.author);
            tv_review_content.setText(review.content);
        }

        @Override
        public void onClick(View v) {

            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(review.url));
            context.startActivity(webIntent);
        }
    }
}
