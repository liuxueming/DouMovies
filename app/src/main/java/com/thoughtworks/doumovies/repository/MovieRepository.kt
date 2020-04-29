package com.thoughtworks.doumovies.repository

import android.content.Context
import com.thoughtworks.doumovies.http.MovieHttp
import com.thoughtworks.doumovies.model.*
import com.thoughtworks.doumovies.model.http.Details
import com.thoughtworks.doumovies.model.http.Rating
import com.thoughtworks.doumovies.model.http.WeeklyMovieResponse
import com.thoughtworks.doumovies.repository.room.config.DbInstance
import com.thoughtworks.doumovies.repository.room.entity.MovieItem
import com.thoughtworks.doumovies.repository.room.entity.MoviePeople
import com.thoughtworks.doumovies.repository.room.entity.MoviePhoto
import java.math.RoundingMode
import java.text.DecimalFormat

class MovieRepository(context: Context) {
    val db = DbInstance.getDbInstance(context)
    val movieItemDao = db.movieItemDao()
    val moviePeopleDao = db.moviePeopleDao()
    val moviePhotoDao = db.moviePhotoDao()
    val movieHttp = MovieHttp()

    fun getWeeklyMovieFromDb(): List<WeeklyMovieItem> {
        val result = mutableListOf<WeeklyMovieItem>()
        val movieItems = movieItemDao.findAll()
        movieItems.forEach {
            val moviePeoples = moviePeopleDao.findByMovieId(it.movieId)
            val moviePhotos = moviePhotoDao.findByMovieId(it.movieId)
            result.add(mapToWeeklyMovieItem(it, moviePeoples, moviePhotos))
        }
        return result
    }

    fun saveWeeklyMovieToDb(mapToWeeklyMovieItems: List<WeeklyMovieItem>) {
        mapToWeeklyMovieItems.forEach {
            var movieItem = MovieItem(
                id = null,
                movieId = it.movieId,
                delta = it.delta,
                rank = it.rank,
                genres = it.genres.joinToString(","),
                originalTitle = it.originalTitle,
                ratingAverage = it.rating.average,
                ratingStars = it.rating.stars,
                ratingGoodRate = it.rating.goodRate,
                title = it.title,
                year = it.year,
                imageLarge = it.imageLarge,
                imageMedium = it.imageMedium,
                imageSmall = it.imageSmall,
                summary = it.summary,
                countries = it.countries?.joinToString(",")
            )

            var moviePeoples = it.casts
                .map {
                    MoviePeople(null, movieItem.movieId, "cast", it.name, it.avatarLarge, it.avatarMedium, it.avatarSmall)
                } as MutableList<MoviePeople>

            val directors = it.directors
                .map {
                    MoviePeople(null, movieItem.movieId, "director", it.name, it.avatarLarge, it.avatarMedium, it.avatarSmall)
                } as MutableList<MoviePeople>

            moviePeoples.addAll(directors)

            it.photos?.let {
                val moviePhotos = it.map {
                    MoviePhoto(null, movieItem.movieId, it.alt, it.cover, it.icon, it.image, it.thumb)
                }
                moviePhotoDao.insert(moviePhotos)
            }

            movieItemDao.insert(movieItem)
            moviePeopleDao.insert(moviePeoples)
        }
    }

    fun mapToWeeklyMovieItem(weeklyMovieResponse: WeeklyMovieResponse): List<WeeklyMovieItem> {
        return weeklyMovieResponse.subjects.map {
            var weeklyMovieItem = WeeklyMovieItem(
                movieId = it.subject.id,
                delta = it.delta,
                rank = it.rank,
                casts = it.subject.casts.map { WeeklyMoviePeople(it.name, it.avatars?.large, it.avatars?.medium, it.avatars?.small) },
                directors = it.subject.directors.map { WeeklyMoviePeople(it.name, it.avatars?.large, it.avatars?.medium, it.avatars?.small) },
                genres = it.subject.genres,
                originalTitle = it.subject.originalTitle,
                rating = WeeklyMovieRating(it.subject.rating.average, it.subject.rating.stars, getGoodRate(it.subject.rating)),
                title = it.subject.title,
                year = it.subject.year,
                photos = null,
                imageLarge = it.subject.images.large,
                imageMedium = it.subject.images.medium,
                imageSmall = it.subject.images.small,
                summary = null,
                countries = null
            )
            movieHttp.getMovieDetail(it.subject.id, { response ->
                run {
                    weeklyMovieItem.summary = response.summary
                    val moviePhotos = response.photos.map {
                        WeeklyMoviePhoto(it.alt, it.cover, it.icon, it.image, it.thumb)
                    }
                    weeklyMovieItem.photos = moviePhotos
                    weeklyMovieItem.countries = response.countries
                }
            }, {})
            weeklyMovieItem
        }
    }

    fun mapToWeeklyMovieItem(
        movieItem: MovieItem,
        moviePeoples: List<MoviePeople>,
        moviePhotos: List<MoviePhoto>
    ): WeeklyMovieItem {
        val casts = moviePeoples
            .filter { it.type == "cast" }
            .map { WeeklyMoviePeople(it.name, it.avatarLarge, it.avatarMedium, it.avatarSmall) }

        val directors = moviePeoples
            .filter { it.type == "director" }
            .map { WeeklyMoviePeople(it.name, it.avatarLarge, it.avatarMedium, it.avatarSmall) }

        val photos =
            moviePhotos.map { WeeklyMoviePhoto(it.alt, it.cover, it.icon, it.image, it.thumb) }
        return WeeklyMovieItem(
            movieId = movieItem.movieId,
            delta = movieItem.delta,
            rank = movieItem.rank,
            casts = casts,
            directors = directors,
            genres = movieItem.genres.split(","),
            originalTitle = movieItem.originalTitle,
            rating = WeeklyMovieRating(movieItem.ratingAverage, movieItem.ratingStars, movieItem.ratingGoodRate),
            title = movieItem.title,
            year = movieItem.year,
            photos = photos,
            imageLarge = movieItem.imageLarge,
            imageMedium = movieItem.imageMedium,
            imageSmall = movieItem.imageSmall,
            summary = movieItem.summary,
            countries = movieItem.countries?.split(",")
        )
    }

    fun getGoodRate(rating: Rating): Double {
        val details = rating.details
        var sum = details.fourScore + details.fiveScore + details.threeScore + details.secondScore + details.firstScore
        val goodRate = (details.fourScore + details.fiveScore) / sum
        val format = DecimalFormat("0.##")
        format.roundingMode = RoundingMode.FLOOR
        return format.format(goodRate).toDouble()
    }

}