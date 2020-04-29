package com.thoughtworks.doumovies.repository.room.config

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thoughtworks.doumovies.repository.room.dao.MovieItemDao
import com.thoughtworks.doumovies.repository.room.dao.MoviePeopleDao
import com.thoughtworks.doumovies.repository.room.dao.MoviePhotoDao
import com.thoughtworks.doumovies.repository.room.entity.MovieItem
import com.thoughtworks.doumovies.repository.room.entity.MoviePeople
import com.thoughtworks.doumovies.repository.room.entity.MoviePhoto

@Database(entities = [MovieItem::class, MoviePeople::class, MoviePhoto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieItemDao(): MovieItemDao
    abstract fun moviePeopleDao(): MoviePeopleDao
    abstract fun moviePhotoDao(): MoviePhotoDao
}