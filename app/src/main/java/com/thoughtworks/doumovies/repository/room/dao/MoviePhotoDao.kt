package com.thoughtworks.doumovies.repository.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.thoughtworks.doumovies.repository.room.entity.MovieItem
import com.thoughtworks.doumovies.repository.room.entity.MoviePeople
import com.thoughtworks.doumovies.repository.room.entity.MoviePhoto

@Dao
interface MoviePhotoDao {
    @Query("SELECT * FROM movie_photo WHERE movie_id=:movieId")
    fun findByMovieId(movieId: String): List<MoviePhoto>

    @Insert
    fun insert(moviePhoto: MoviePhoto)

    @Insert
    fun insert(moviePhotos: List<MoviePhoto>)
}