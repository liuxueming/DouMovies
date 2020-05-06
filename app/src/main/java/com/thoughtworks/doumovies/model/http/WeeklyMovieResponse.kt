package com.thoughtworks.doumovies.model.http

import com.google.gson.annotations.SerializedName

data class WeeklyMovieResponse(
    val subjects: List<WeeklyMovieItem>,
    val title: String
)

data class WeeklyMovieItem(
    val delta: Int,
    val rank: Int,
    val subject: SubjectX
){
    fun getRatingValue(): Double {
        return this.subject.rating.average / 2.0
    }

    fun getRatingAverageText(): String {
        return this.subject.rating.average.toString()
    }

    fun getIntroText(): String {
        val outputStr = StringBuilder(this.subject.year).append(" / ")
        this.subject.countries?.forEach {
            outputStr.append(it)
            outputStr.append(" / ")
        }
        this.subject.genres.forEach {
            outputStr.append(it)
            outputStr.append(" ")
        }
        outputStr.append("/ ")
        this.subject.directors?.forEach {
            outputStr.append(it.name)
            outputStr.append(" ")
        }

        if (this.subject.casts.isNullOrEmpty()) {
            return outputStr.toString()
        }

        outputStr.append("/ ")
        this.subject.casts?.forEach {
            outputStr.append(it.name)
            outputStr.append(" ")
        }

        return outputStr.toString()
    }

    fun getPositiveRate() : String {
        val details = this.subject.rating.details
        var sum = details.fourScore + details.fiveScore + details.threeScore + details.secondScore + details.firstScore
        val goodRate = ((details.fourScore + details.fiveScore) / sum).times(100)

        val outputStr =
            StringBuilder(String.format("%.0f", goodRate)).append("%").append("好评")
        return outputStr.toString()
    }
}

data class SubjectX(
    val alt: String? = null,
    val casts: List<People>?,
    @SerializedName("collect_count")
    val collectCount: Int? = null,
    val directors: List<People>?,
    val durations: List<String>? = null,
    val genres: List<String>,
    @SerializedName("has_video")
    val hasVideo: Boolean? = null,
    val id: String,
    val images: Images,
    @SerializedName("mainland_pubdate")
    val mainlandPubdate: String? = null,
    @SerializedName("original_title")
    val originalTitle: String,
    val pubdates: List<String>? = null,
    val rating: Rating,
    val subtype: String? = null,
    val title: String,
    val year: String,
    var summary: String? = null,
    var countries: List<String>? = null,
    var photos: List<Photo>? = null
)
