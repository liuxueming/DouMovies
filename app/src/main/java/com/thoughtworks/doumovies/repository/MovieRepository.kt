package com.thoughtworks.doumovies.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.thoughtworks.doumovies.model.http.*
import com.thoughtworks.doumovies.repository.room.config.DbInstance
import com.thoughtworks.doumovies.repository.room.entity.MovieItem

class MovieRepository() {
    private val movieItemDao = DbInstance.getMovieItemDao()
    private val gson = Gson()

    fun getWeeklyMovieFromDb(): List<WeeklyMovieItem> {
        val movieItems = movieItemDao.findAll()
        return movieItems.map { mapToWeeklyMovieItem(it) }
    }

    fun saveWeeklyMovieToDb(weeklyMovieResponse: WeeklyMovieResponse) {
        movieItemDao.insert(mapToMovieItem(weeklyMovieResponse))
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

    fun updateWeeklyMovieItem(movieDetail: MovieDetailResponse) {
        val photos = Gson().toJson(movieDetail.photos)
        val countries = movieDetail.countries.joinToString(",")
        movieItemDao.updateSummaryAndCountriesByMovieId(movieDetail.id, movieDetail.summary, countries, photos)
    }

}