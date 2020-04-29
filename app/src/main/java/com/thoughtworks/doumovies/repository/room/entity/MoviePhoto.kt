package com.thoughtworks.doumovies.repository.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_photo")
data class MoviePhoto (
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @ColumnInfo(name = "movie_id") val movieId: String,
    @ColumnInfo(name = "alt") val alt: String,
    @ColumnInfo(name = "cover") val cover: String,
    @ColumnInfo(name = "icon") val icon: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "thumb") val thumb: String
)
