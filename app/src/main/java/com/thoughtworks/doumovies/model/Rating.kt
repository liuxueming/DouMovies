package com.thoughtworks.doumovies.model

data class Rating(
    val average: Double,
    val details: Details,
    val max: Int,
    val min: Int,
    val stars: String
)