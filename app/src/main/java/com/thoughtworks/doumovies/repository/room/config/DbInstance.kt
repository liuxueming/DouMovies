package com.thoughtworks.doumovies.repository.room.config

import android.content.Context
import androidx.room.Room

object DbInstance {
    var db: AppDatabase? = null

    fun getDbInstance(context: Context): AppDatabase {
        if (db == null) {
            db = Room.databaseBuilder(
                context,
                AppDatabase::class.java, "database-douban"
            ).allowMainThreadQueries().build()
        }
        return db as AppDatabase
    }
}