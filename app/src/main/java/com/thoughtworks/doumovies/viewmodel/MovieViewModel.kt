package com.thoughtworks.doumovies.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.thoughtworks.doumovies.utils.MovieHttpUtil
import com.thoughtworks.doumovies.model.http.MovieDetailResponse
import com.thoughtworks.doumovies.model.http.WeeklyMovieItem
import com.thoughtworks.doumovies.repository.MovieRepository

class MovieViewModel(application: Application) : AndroidViewModel(application) {
    private val movieRepository = MovieRepository()
    private val movieHttp = MovieHttpUtil()
    val weeklyMovieLiveData = MutableLiveData<List<WeeklyMovieItem>>()
    val movieDetailLiveData = MutableLiveData<MovieDetailResponse>()

    fun getWeeklyMovie() {
        val weeklyMovieFromDb = movieRepository.getWeeklyMovieFromDb()
        if (weeklyMovieFromDb.isEmpty()) {
            movieHttp.getWeeklyMovies({ weeklyMovieResponse ->
                weeklyMovieLiveData.postValue(weeklyMovieResponse.subjects)
                getAndUpdateDetailInfo(weeklyMovieResponse.subjects)
                movieRepository.saveWeeklyMovieToDb(weeklyMovieResponse)
            }, {})
        } else {
            weeklyMovieLiveData.postValue(weeklyMovieFromDb)
        }
    }

    fun getMovieDetail(movieId: String) {
        movieHttp.getMovieDetail(movieId, { movieDetail ->
            movieDetailLiveData.postValue(movieDetail)
        }, {})
    }

    private fun getAndUpdateDetailInfo(weeklyMovieItems: List<WeeklyMovieItem>) {
        weeklyMovieItems.forEach { weeklyMovieItem ->
            movieHttp.getMovieDetail(weeklyMovieItem.subject.id, {
                weeklyMovieItem.subject.summary = it.summary
                weeklyMovieItem.subject.photos = it.photos
                weeklyMovieItem.subject.countries = it.countries
                weeklyMovieLiveData.postValue(weeklyMovieItems)
                movieRepository.updateWeeklyMovieItem(it)
            }, {})
        }
    }
}