package io.nanodegree.andrea.popularmovies.persistence;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import io.nanodegree.andrea.popularmovies.model.Movie;

/**
 * Created by Andrea Loddo (andrea@evenly.io) on 03.01.2019
 * for Evenly GmbH
 * <p>
 * Copyright (c) 2019 Evenly GmbH,
 * all rights reserved
 */
@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie")
    List<Movie> loadAllFavorites();

    @Query("SELECT * FROM movie WHERE id LIKE :id")
    Movie getMovie(String id);

    @Insert
    void insertMovie(Movie movie);

    @Delete
    void deleteMovie(Movie movie);
}
