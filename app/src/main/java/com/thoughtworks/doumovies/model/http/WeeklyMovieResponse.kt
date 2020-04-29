package com.thoughtworks.doumovies.model.http

import com.google.gson.annotations.SerializedName

data class WeeklyMovieResponse(
    val subjects: List<Subject>,
    val title: String
)

data class Subject(
    val delta: Int,
    val rank: Int,
    val subject: SubjectX
)

data class SubjectX(
    val alt: String,
    val casts: List<People>,
    @SerializedName("collect_count")
    val collectCount: Int,
    val directors: List<People>,
    val durations: List<String>,
    val genres: List<String>,
    @SerializedName("has_video")
    val hasVideo: Boolean,
    val id: String,
    val images: Images,
    @SerializedName("mainland_pubdate")
    val mainlandPubdate: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val pubdates: List<String>,
    val rating: Rating,
    val subtype: String,
    val title: String,
    val year: String
)
