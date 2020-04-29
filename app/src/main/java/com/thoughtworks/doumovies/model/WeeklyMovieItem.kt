package com.thoughtworks.doumovies.model

data class WeeklyMovieItem(
    val movieId: String,
    val delta: Int,
    val rank: Int,
    val casts: List<WeeklyMoviePeople>?,
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
) {
    fun getRatingValue(): Double {
        return this.rating.average / 2.0
    }

    fun getRatingAverageText(): String {
        return this.rating.average.toString()
    }

    fun getIntroText(): String {
        val outputStr = StringBuilder(this.year).append(" / ")
        this.countries?.forEach {
            outputStr.append(it)
            outputStr.append(" / ")
        }
        this.genres.forEach {
            outputStr.append(it)
            outputStr.append(" ")
        }
        outputStr.append("/ ")
        this.directors.forEach {
            outputStr.append(it.name)
            outputStr.append(" ")
        }

        if (this.casts.isNullOrEmpty()) {
            return outputStr.toString()
        }

        outputStr.append("/ ")
        this.casts?.forEach {
            outputStr.append(it.name)
            outputStr.append(" ")
        }

        return outputStr.toString()
    }

    fun getPositiveRate() : String {
        val outputStr =
            StringBuilder(String.format("%.0f", this.rating.goodRate.times(100))).append("%").append("好评")
        return outputStr.toString()
    }
}

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
