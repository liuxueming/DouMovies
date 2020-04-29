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
)

data class SubjectX(
    val alt: String? = null,
    val casts: List<People>,
    @SerializedName("collect_count")
    val collectCount: Int? = null,
    val directors: List<People>,
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
