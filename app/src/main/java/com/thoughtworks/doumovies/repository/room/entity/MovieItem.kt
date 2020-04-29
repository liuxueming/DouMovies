package com.thoughtworks.doumovies.repository.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_item")
data class MovieItem (
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @ColumnInfo(name = "movie_id") val movieId: String,
    @ColumnInfo(name = "delta") val delta: Int,
    @ColumnInfo(name = "rank") val rank: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "year") val year: String,
    @ColumnInfo(name = "original_title") val originalTitle: String,
    @ColumnInfo(name = "rating_average") val ratingAverage: Double,
    @ColumnInfo(name = "rating_stars") val ratingStars: String,
    @ColumnInfo(name = "rating_good_rate") val ratingGoodRate: Double,
    @ColumnInfo(name = "genres") val genres: String,
    @ColumnInfo(name = "summary") var summary: String?,
    @ColumnInfo(name = "image_large") val imageLarge: String,
    @ColumnInfo(name = "image_medium") val imageMedium: String,
    @ColumnInfo(name = "image_small") val imageSmall: String,
    @ColumnInfo(name = "countries") val countries: String?
)