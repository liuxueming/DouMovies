package com.thoughtworks.doumovies.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.thoughtworks.doumovies.http.MovieHttp
import com.thoughtworks.doumovies.model.WeeklyMovieItem
import com.thoughtworks.doumovies.model.WeeklyMoviePhoto
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
//                getAndUpdateDetailInfo(weeklyMovieItems)
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

    fun getAndUpdateDetailInfo(weeklyMovieItems: List<WeeklyMovieItem>) {
        weeklyMovieItems.forEach {
            movieHttp.getMovieDetail(it.movieId, { movieDetail ->
                it.summary = movieDetail.summary
                val moviePhotos = movieDetail.photos.map {
                    WeeklyMoviePhoto(it.alt, it.cover, it.icon, it.image, it.thumb)
                }
                it.photos = moviePhotos
                it.countries = movieDetail.countries
                println(weeklyMovieItems[0].summary)
                weeklyMovieLiveData.postValue(weeklyMovieItems)
            }, {})
        }
    }
}