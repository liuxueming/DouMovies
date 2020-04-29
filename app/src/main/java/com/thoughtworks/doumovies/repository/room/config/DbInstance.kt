package com.thoughtworks.doumovies.repository.room.config

import androidx.room.Room
import com.thoughtworks.doumovies.MyApplication
import com.thoughtworks.doumovies.repository.room.dao.MovieItemDao

object DbInstance {
    val db = Room.databaseBuilder(
        MyApplication.getContext(),
        AppDatabase::class.java, "database-douban"
    ).allowMainThreadQueries().build()

    fun getMovieItemDao(): MovieItemDao {
        return db.movieItemDao()
    }
}