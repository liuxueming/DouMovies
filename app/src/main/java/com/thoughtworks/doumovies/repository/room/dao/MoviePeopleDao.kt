package com.thoughtworks.doumovies.repository.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.thoughtworks.doumovies.repository.room.entity.MovieItem
import com.thoughtworks.doumovies.repository.room.entity.MoviePeople

@Dao
interface MoviePeopleDao {
    @Query("SELECT * FROM movie_people WHERE movie_id=:movieId")
    fun findByMovieId(movieId: String): List<MoviePeople>

    @Insert
    fun insert(moviePeople: MoviePeople)

    @Insert
    fun insert(moviePeoples: List<MoviePeople>)
}