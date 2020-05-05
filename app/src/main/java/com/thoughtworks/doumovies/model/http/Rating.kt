package com.thoughtworks.doumovies.model.http

data class Rating(
    val average: Double,
    val details: Details,
    val max: Int,
    val min: Int,
    val stars: String
) {
    fun getAverageStr() : String {
        return this.average.toString()
    }

    fun getStarsFloat() : Float {
        return this.stars.toFloat() / 10
    }
}