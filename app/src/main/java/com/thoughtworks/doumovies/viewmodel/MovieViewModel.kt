package com.thoughtworks.doumovies.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.thoughtworks.doumovies.model.http.MovieDetailResponse
import com.thoughtworks.doumovies.model.http.WeeklyMovieItem
import com.thoughtworks.doumovies.repository.MovieRepository

class MovieViewModel(application: Application, private val movieRepository: MovieRepository) : AndroidViewModel(application) {
    val weeklyMovieLiveData = MutableLiveData<List<WeeklyMovieItem>>()
    val movieDetailLiveData = MutableLiveData<MovieDetailResponse>()

    fun getWeeklyMovie() {
        movieRepository.getWeeklyMovie { weeklyMovies ->
            weeklyMovieLiveData.postValue(weeklyMovies)
        }
    }

    fun getMovieDetail(movieId: String) {
        movieRepository.getMovieDetail(movieId) { movieDetail ->
            movieDetailLiveData.postValue(movieDetail)
        }
    }
}