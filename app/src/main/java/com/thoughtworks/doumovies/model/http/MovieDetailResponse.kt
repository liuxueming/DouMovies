package com.thoughtworks.doumovies.model.http

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    val casts: List<People>,
    @SerializedName("comments_count")
    val commentsCount: Int? = null,
    val countries: List<String>,
    val directors: List<People>,
    val durations: List<String>,
    val genres: List<String>,
    val id: String,
    val images: Images,
    val languages: List<String>,
    @SerializedName("mainland_pubdate")
    val mainlandPubdate: String? = null,
    @SerializedName("original_title")
    val originalTitle: String,
    val photos: List<Photo>? = null,
    @SerializedName("photos_count")
    val photosCount: Int? = null,
    @SerializedName("popular_comments")
    val popularComments: List<PopularComment>? = null,
    @SerializedName("popular_reviews")
    val popularReviews: List<PopularReview>? = null,
    val pubdate: String? = null,
    val pubdates: List<String>,
    val rating: Rating,
    val summary: String,
    val tags: List<String>,
    val title: String,
    val year: String
) {
    fun getYearStr() : String {
        return StringBuilder("(").append(this.year).append(")").toString()
    }

    fun getCombineInfo() : String {
        val combineInfo = StringBuilder(this.countries.joinToString(" ")).append(" / ")
        combineInfo.append(this.genres.joinToString(" ")).append(" / ")
        combineInfo.append("上映时间: ")
        combineInfo.append(this.pubdates[0]).append(" / ")
        combineInfo.append("片长: ")
        combineInfo.append(this.durations[0])
        return combineInfo.toString()
    }

    fun getCastAndDirectors() : List<People> {
        this.casts.forEach{ it.role = "演员" }
        this.directors.forEach{ it.role = "导演" }
        val castAndDirectors = this.directors.toMutableList()
        castAndDirectors.addAll(this.casts)
        return castAndDirectors
    }
}

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
    val rating: RatingX,
    val subject_id: String,
    val useful_count: Int
)

data class PopularReview(
    val alt: String,
    val author: Author,
    val id: String,
    val rating: RatingX,
    val subject_id: String,
    val summary: String,
    val title: String
)

data class Author(
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