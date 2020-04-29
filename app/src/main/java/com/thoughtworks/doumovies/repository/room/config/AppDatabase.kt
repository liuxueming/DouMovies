package com.thoughtworks.doumovies.repository.room.config

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thoughtworks.doumovies.repository.room.dao.MovieItemDao
import com.thoughtworks.doumovies.repository.room.entity.MovieItem

@Database(entities = [MovieItem::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieItemDao(): MovieItemDao
}