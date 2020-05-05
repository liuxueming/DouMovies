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
) {
    fun getSum() : Double {
        return firstScore + secondScore + threeScore + fourScore + firstScore
    }

    fun getPerThousandFor1() : Int {
        return (firstScore / this.getSum() * 1000).toInt()
    }

    fun getPerThousandFor2() : Int {
        return (secondScore / this.getSum() * 1000).toInt()
    }

    fun getPerThousandFor3() : Int {
        return (threeScore / this.getSum() * 1000).toInt()
    }

    fun getPerThousandFor4() : Int {
        return (fourScore / this.getSum() * 1000).toInt()
    }

    fun getPerThousandFor5() : Int {
        return (fiveScore / this.getSum() * 1000).toInt()
    }
}