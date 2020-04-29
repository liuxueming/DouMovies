package com.thoughtworks.doumovies.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.thoughtworks.doumovies.http.MovieHttp
import com.thoughtworks.doumovies.model.WeeklyMovieItem
import com.thoughtworks.doumovies.model.http.MovieDetailResponse
import com.thoughtworks.doumovies.repository.MovieRepository

class MovieViewModel(context: Context, application: Application) : AndroidViewModel(application) {
    private val movieRepository = MovieRepository(context)
    private val movieHttp = MovieHttp()
    val weeklyMovieLiveData = MutableLiveData<List<WeeklyMovieItem>>()
    val movieDetailLiveData = MutableLiveData<MovieDetailResponse>()

    fun getWeeklyMovie() {
        val weeklyMovieFromDb = movieRepository.getWeeklyMovieFromDb()
        if (weeklyMovieFromDb.isEmpty()) {
            movieHttp.getWeeklyMovies({ weeklyMovieResponse ->
                val weeklyMovieItems = movieRepository.mapToWeeklyMovieItem(weeklyMovieResponse)
                weeklyMovieLiveData.postValue(weeklyMovieItems)
                movieRepository.saveWeeklyMovieToDb(weeklyMovieItems)
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
}