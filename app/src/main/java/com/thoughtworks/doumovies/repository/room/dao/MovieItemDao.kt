package com.thoughtworks.doumovies.repository.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.thoughtworks.doumovies.repository.room.entity.MovieItem

@Dao
interface MovieItemDao {
    @Query("SELECT * FROM movie_item")
    fun findAll(): List<MovieItem>

    @Query("UPDATE movie_item SET summary=:summary, countries=:countries , photos=:photos WHERE movie_id=:movieId")
    fun updateSummaryAndCountriesByMovieId(movieId: String, summary: String?, countries: String?, photos: String?)

    @Insert
    fun insert(movieItems: List<MovieItem>)
}