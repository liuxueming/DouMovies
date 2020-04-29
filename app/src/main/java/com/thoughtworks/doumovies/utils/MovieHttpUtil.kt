package com.thoughtworks.doumovies.utils

import com.google.gson.Gson
import com.thoughtworks.doumovies.model.http.MovieDetailResponse
import com.thoughtworks.doumovies.model.http.WeeklyMovieResponse
import okhttp3.*
import java.io.IOException

class MovieHttpUtil {
    companion object {
        const val WEEKLY_URL = "https://douban.uieee.com//v2/movie/weekly"
        const val DETAIL_URL_PREFIX = "https://douban.uieee.com/v2/movie/subject/"
        val client by lazy(LazyThreadSafetyMode.NONE) { OkHttpClient.Builder().build() }
    }

    fun getWeeklyMovies(success: (weeklyMovie: WeeklyMovieResponse) -> Unit, fail: () -> Unit) {
        val request = Request.Builder()
            .url(WEEKLY_URL)
            .get()
            .build()
        val newCall = client.newCall(request)
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
        val newCall = client.newCall(request)
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