package com.thoughtworks.doumovies.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.thoughtworks.doumovies.model.MovieDetail
import com.thoughtworks.doumovies.model.WeeklyMovie
import com.thoughtworks.doumovies.repository.MovieRepository

class MovieViewModel(application: Application) : AndroidViewModel(application) {
    private val movieRepository = MovieRepository()
    val weeklyMovieLiveData = MutableLiveData<WeeklyMovie>()
    val movieDetailLiveData = MutableLiveData<MovieDetail>()

    fun getWeeklyMovie() {
        movieRepository.getWeeklyMovies({ weeklyMovie ->
            weeklyMovieLiveData.postValue(weeklyMovie)
        }, {})
    }

    fun getMovieDetail(movieId: Int) {
        movieRepository.getMovieDetail(movieId, { movieDetail ->
            movieDetailLiveData.postValue(movieDetail)
        }, {})
    }
}