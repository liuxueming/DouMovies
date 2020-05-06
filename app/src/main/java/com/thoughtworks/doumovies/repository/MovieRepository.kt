package com.thoughtworks.doumovies.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.thoughtworks.doumovies.model.http.*
import com.thoughtworks.doumovies.repository.room.dao.MovieItemDao
import com.thoughtworks.doumovies.repository.room.entity.MovieItem
import com.thoughtworks.doumovies.utils.MovieHttpUtil

class MovieRepository(private val movieItemDao: MovieItemDao) {
    private val gson = Gson()
    private val movieHttp = MovieHttpUtil()

    fun getWeeklyMovie(success: (weeklyMovies: List<WeeklyMovieItem>) -> Unit) {
        val weeklyMovies = getWeeklyMovieFromDb()
        if (weeklyMovies.isEmpty()) {
            movieHttp.getWeeklyMovies({ weeklyMovieResponse ->
                success(weeklyMovieResponse.subjects)
                getAndUpdateDetailInfo(weeklyMovieResponse.subjects) { response -> success(response)}
                saveWeeklyMovieToDb(weeklyMovieResponse)
            }, {})
        } else {
            success(weeklyMovies)
        }
    }

    fun getMovieDetail(movieId: String, success: (movieDetail: MovieDetailResponse) -> Unit) {
        movieHttp.getMovieDetail(movieId, { movieDetail ->
            success(movieDetail)
        }, {})
    }

    private fun getWeeklyMovieFromDb(): List<WeeklyMovieItem> {
        val movieItems = movieItemDao.findAll()
        return movieItems.map { mapToWeeklyMovieItem(it) }
    }

    private fun saveWeeklyMovieToDb(weeklyMovieResponse: WeeklyMovieResponse) {
        movieItemDao.insert(mapToMovieItem(weeklyMovieResponse))
    }

    private fun getAndUpdateDetailInfo(weeklyMovieItems: List<WeeklyMovieItem>, success: (weeklyMovies: List<WeeklyMovieItem>) -> Unit) {
        weeklyMovieItems.forEach { weeklyMovieItem ->
            movieHttp.getMovieDetail(weeklyMovieItem.subject.id, {
                weeklyMovieItem.subject.summary = it.summary
                weeklyMovieItem.subject.photos = it.photos
                weeklyMovieItem.subject.countries = it.countries
                success(weeklyMovieItems)
                updateWeeklyMovieItem(it)
            }, {})
        }
    }

    private fun mapToMovieItem(weeklyMovieResponse: WeeklyMovieResponse): List<MovieItem> {
        return weeklyMovieResponse.subjects.map {
            MovieItem(
                movieId = it.subject.id,
                delta = it.delta,
                rank = it.rank,
                casts = Gson().toJson(it.subject.casts),
                directors = Gson().toJson(it.subject.directors),
                genres = it.subject.genres.joinToString(","),
                originalTitle = it.subject.originalTitle,
                rating = Gson().toJson(it.subject.rating),
                title = it.subject.title,
                year = it.subject.year,
                photos = null,
                images = Gson().toJson(it.subject.images)
            )
        }
    }

    private fun mapToWeeklyMovieItem(movieItem: MovieItem): WeeklyMovieItem {
        val subjectX = SubjectX(
            id = movieItem.movieId,
            casts = gson.fromJson(movieItem.casts, object :TypeToken<List<People>>(){}.type),
            directors = gson.fromJson(movieItem.directors, object :TypeToken<List<People>>(){}.type),
            genres = movieItem.genres.split(","),
            images = gson.fromJson(movieItem.images, Images::class.java),
            originalTitle = movieItem.originalTitle,
            rating = gson.fromJson(movieItem.rating, Rating::class.java),
            title = movieItem.title,
            year = movieItem.year,
            summary = movieItem.summary,
            countries = movieItem.countries?.split(","),
            photos = gson.fromJson(movieItem.photos, object :TypeToken<List<Photo>>(){}.type)
        )
        return WeeklyMovieItem(
            delta = movieItem.delta,
            rank = movieItem.rank,
            subject = subjectX
        )
    }

    private fun updateWeeklyMovieItem(movieDetail: MovieDetailResponse) {
        val photos = Gson().toJson(movieDetail.photos)
        val countries = movieDetail.countries.joinToString(",")
        movieItemDao.updateSummaryAndCountriesByMovieId(movieDetail.id, movieDetail.summary, countries, photos)
    }

}