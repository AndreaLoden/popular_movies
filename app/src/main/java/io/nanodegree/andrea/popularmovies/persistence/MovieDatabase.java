package io.nanodegree.andrea.popularmovies.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import io.nanodegree.andrea.popularmovies.model.Movie;

/**
 * Created by Andrea Loddo (andrea@evenly.io) on 03.01.2019
 * for Evenly GmbH
 * <p>
 * Copyright (c) 2019 Evenly GmbH,
 * all rights reserved
 */

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {

    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "moviedatabase";
    private static MovieDatabase sMovieDatabase;

    public static MovieDatabase getInstance(Context context) {
        if (sMovieDatabase == null) {
            synchronized (LOCK) {
                sMovieDatabase = Room.databaseBuilder(
                        context.getApplicationContext(),
                        MovieDatabase.class,
                        MovieDatabase.DATABASE_NAME)
                        .build();
            }
        }

        return sMovieDatabase;
    }

    public abstract MovieDao getMovieDao();
}
