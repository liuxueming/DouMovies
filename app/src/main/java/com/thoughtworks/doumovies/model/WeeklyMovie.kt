package com.thoughtworks.doumovies.model

import com.google.gson.annotations.SerializedName

data class WeeklyMovie(
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
    val casts: List<Cast>,
    @SerializedName("collect_count")
    val collectCount: Int,
    val directors: List<Director>,
    val durations: List<String>,
    val genres: List<String>,
    val has_video: Boolean,
    val id: String,
    val images: Images,
    @SerializedName("mainland_pubdate")
    val mainlandPubdate: String,
    @SerializedName("originalTitle")
    val original_title: String,
    val pubdates: List<String>,
    val rating: Rating,
    val subtype: String,
    val title: String,
    val year: String
)
