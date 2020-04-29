package com.thoughtworks.doumovies.repository.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.thoughtworks.doumovies.repository.room.entity.MovieItem

@Dao
interface MovieItemDao {
    @Query("SELECT * FROM movie_item")
    fun findAll(): List<MovieItem>

    @Insert
    fun insert(movieItem: MovieItem)
}