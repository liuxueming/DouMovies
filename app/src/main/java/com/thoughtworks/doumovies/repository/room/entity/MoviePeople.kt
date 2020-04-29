package com.thoughtworks.doumovies.repository.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_people")
data class MoviePeople (
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @ColumnInfo(name = "movie_id") val movieId: String,
    @ColumnInfo(name = "type") val type: String, //cast: 演员 director: 导演
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "avatar_large") val avatarLarge: String?,
    @ColumnInfo(name = "avatar_medium") val avatarMedium: String?,
    @ColumnInfo(name = "avatar_small") val avatarSmall: String?
)
