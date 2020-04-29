package com.thoughtworks.doumovies.model.http

import com.google.gson.annotations.SerializedName

data class Details(
    @SerializedName("1")
    val firstScore: Double,
    @SerializedName("2")
    val secondScore: Double,
    @SerializedName("3")
    val threeScore: Double,
    @SerializedName("4")
    val fourScore: Double,
    @SerializedName("5")
    val fiveScore: Double
)