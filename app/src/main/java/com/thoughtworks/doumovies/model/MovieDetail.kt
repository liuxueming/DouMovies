package com.thoughtworks.doumovies.model

import com.google.gson.annotations.SerializedName

data class MovieDetail(
    val aka: List<String>,
    val alt: String,
    @SerializedName("mainland_pubdate")
    val blooper_urls: List<Any>,
    val bloopers: List<Any>,
    val casts: List<Cast>,
    val clip_urls: List<Any>,
    val clips: List<Any>,
    val collect_count: Int,
    val collection: Any,
    val comments_count: Int,
    val countries: List<String>,
    val current_season: Any,
    val directors: List<Director>,
    val do_count: Any,
    val douban_site: String,
    val durations: List<String>,
    val episodes_count: Any,
    val genres: List<String>,
    val has_schedule: Boolean,
    val has_ticket: Boolean,
    val has_video: Boolean,
    val id: String,
    val images: Images,
    val languages: List<String>,
    val mainland_pubdate: String,
    val mobile_url: String,
    val original_title: String,
    val photos: List<Photo>,
    val photos_count: Int,
    val popular_comments: List<PopularComment>,
    val popular_reviews: List<PopularReview>,
    val pubdate: String,
    val pubdates: List<String>,
    val rating: RatingXX,
    val ratings_count: Int,
    val reviews_count: Int,
    val schedule_url: String,
    val seasons_count: Any,
    val share_url: String,
    val subtype: String,
    val summary: String,
    val tags: List<String>,
    val title: String,
    val trailer_urls: List<Any>,
    val trailers: List<Any>,
    val videos: List<Video>,
    val website: String,
    val wish_count: Int,
    val writers: List<Writer>,
    val year: String
)

data class Photo(
    val alt: String,
    val cover: String,
    val icon: String,
    val id: String,
    val image: String,
    val thumb: String
)

data class PopularComment(
    val author: Author,
    val content: String,
    val created_at: String,
    val id: String,
    val rating: Rating,
    val subject_id: String,
    val useful_count: Int
)

data class PopularReview(
    val alt: String,
    val author: AuthorX,
    val id: String,
    val rating: RatingX,
    val subject_id: String,
    val summary: String,
    val title: String
)

data class RatingXX(
    val average: Double,
    val details: Details,
    val max: Int,
    val min: Int,
    val stars: String
)

data class Video(
    val need_pay: Boolean,
    val sample_link: String,
    val source: Source,
    val video_id: String
)

data class Writer(
    val alt: String,
    val avatars: AvatarsXX,
    val id: String,
    val name: String,
    val name_en: String
)

data class Author(
    val alt: String,
    val avatar: String,
    val id: String,
    val name: String,
    val signature: String,
    val uid: String
)

data class AuthorX(
    val alt: String,
    val avatar: String,
    val id: String,
    val name: String,
    val signature: String,
    val uid: String
)

data class RatingX(
    val max: Int,
    val min: Int,
    val value: Double
)

data class Source(
    val literal: String,
    val name: String,
    val pic: String
)

data class AvatarsXX(
    val large: String,
    val medium: String,
    val small: String
)