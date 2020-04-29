package com.thoughtworks.doumovies.http

import com.google.gson.Gson
import com.thoughtworks.doumovies.model.http.MovieDetailResponse
import com.thoughtworks.doumovies.model.http.WeeklyMovieResponse
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class MovieHttp {
    val WEEKLY_URL = "https://douban.uieee.com//v2/movie/weekly"
    val DETAIL_URL_PREFIX = "https://douban.uieee.com/v2/movie/subject/"

    fun getWeeklyMovies(success: (weeklyMovie: WeeklyMovieResponse) -> Unit, fail: () -> Unit) {
        val request = Request.Builder()
            .url(WEEKLY_URL)
            .get()
            .build()
        val newCall = ApiClient.client.newCall(request)
        newCall.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                fail()
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val weeklyMovie = Gson().fromJson(response.body?.string(), WeeklyMovieResponse::class.java)
                success(weeklyMovie)
            }
        })
    }

    fun getMovieDetail(
        movieId: String,
        success: (movieDetail: MovieDetailResponse) -> Unit,
        fail: () -> Unit
    ) {
        val request = Request.Builder()
            .url("${DETAIL_URL_PREFIX}/${movieId}")
            .get()
            .build()
        val newCall = ApiClient.client.newCall(request)
        newCall.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                fail()
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val movieDetail = Gson().fromJson(response.body?.string(), MovieDetailResponse::class.java)
                success(movieDetail)
            }
        })
    }
}