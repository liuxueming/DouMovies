package com.thoughtworks.doumovies.model.http

import com.thoughtworks.doumovies.model.http.Details

data class Rating(
    val average: Double,
    val details: Details,
    val max: Int,
    val min: Int,
    val stars: String
)