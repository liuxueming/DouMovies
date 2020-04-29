package com.thoughtworks.doumovies.model

data class WeeklyMovieItem(
    val movieId: String,
    val delta: Int,
    val rank: Int,
    val casts: List<WeeklyMoviePeople>,
    val directors: List<WeeklyMoviePeople>,
    val genres: List<String>,
    val originalTitle: String,
    val rating: WeeklyMovieRating,
    val title: String,
    val year: String,
    var photos: List<WeeklyMoviePhoto>?,
    val imageLarge: String, //影片标题的那张图片
    val imageMedium: String,
    val imageSmall: String,
    var summary: String?,
    var countries: List<String>?
)

data class WeeklyMoviePeople(
    val name: String,
    val avatarLarge: String?,
    val avatarMedium: String?,
    val avatarSmall: String?
)

data class WeeklyMoviePhoto(
    val alt: String,
    val cover: String,
    val icon: String,
    val image: String,
    val thumb: String
)

data class WeeklyMovieRating(
    val average: Double,
    val stars: String,
    val goodRate: Double
)
