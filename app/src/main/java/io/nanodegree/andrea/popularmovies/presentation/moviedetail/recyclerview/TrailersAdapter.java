package io.nanodegree.andrea.popularmovies.presentation.moviedetail.recyclerview;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import io.nanodegree.andrea.popularmovies.data.model.Video;
import java.util.ArrayList;
import java.util.List;

import io.nanodegree.andrea.popularmovies.R;

/**
 * Created by Andrea Loddo (andrea@evenly.io) on 11.05.2018
 * for Evenly GmbH
 * <p>
 * Copyright (c) 2018 Evenly GmbH,
 * all rights reserved
 */
public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.TrailerItemViewHolder> {

    private List<Video> videoList;
    private Context context;

    TrailersAdapter(@NonNull Context context) {
        this.context = context;
        videoList = new ArrayList<>();
    }

    @NonNull
    @Override
    public TrailerItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.video_item, parent, false);

        return new TrailerItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerItemViewHolder holder, int position) {
        holder.bind(videoList.get(position));
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public void setData(List<Video> videos) {
        this.videoList.clear();
        this.videoList.addAll(videos);
    }

    class TrailerItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_video_title;
        Video video;

        TrailerItemViewHolder(View itemView) {
            super(itemView);

            tv_video_title = itemView.findViewById(R.id.video_title);
            itemView.setOnClickListener(this);
        }

        void bind(Video video) {
            this.video = video;
            tv_video_title.setText(video.getName());
        }

        @Override
        public void onClick(View v) {

            Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + video.getKey()));
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + video.getKey()));

            try {
                context.startActivity(appIntent);
            } catch (ActivityNotFoundException ex) {
                context.startActivity(webIntent);
            }
        }
    }
}
