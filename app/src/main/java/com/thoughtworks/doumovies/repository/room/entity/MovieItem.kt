package com.thoughtworks.doumovies.repository.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_item")
data class MovieItem (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "movie_id") val movieId: String,
    @ColumnInfo(name = "delta") val delta: Int,
    @ColumnInfo(name = "rank") val rank: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "year") val year: String,
    @ColumnInfo(name = "original_title") val originalTitle: String,
    @ColumnInfo(name = "genres") val genres: String,
    @ColumnInfo(name = "summary") var summary: String? = null,
    @ColumnInfo(name = "images") val images: String,
    @ColumnInfo(name = "countries") var countries: String? = null,
    @ColumnInfo(name = "casts") val casts: String,
    @ColumnInfo(name = "photos") var photos: String? = null,
    @ColumnInfo(name = "directors") val directors: String,
    @ColumnInfo(name = "rating") val rating: String
)