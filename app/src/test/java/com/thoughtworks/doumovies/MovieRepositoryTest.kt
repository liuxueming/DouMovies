package com.thoughtworks.doumovies

import com.google.gson.Gson
import com.thoughtworks.doumovies.model.http.*
import com.thoughtworks.doumovies.repository.MovieRepository
import com.thoughtworks.doumovies.repository.room.dao.MovieItemDao
import com.thoughtworks.doumovies.repository.room.entity.MovieItem
import com.thoughtworks.doumovies.utils.MovieHttpUtil
import org.junit.Assert
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import java.util.concurrent.CountDownLatch

class MovieRepositoryTest {

    @Test
    fun get_weekly_movie_from_db() {
        val application = mock(MyApplication::class.java)
        MyApplication._context = application

        val movieItemDaoMock = mock(MovieItemDao::class.java)
        val movieItem = buildMovieItem()
        `when`(movieItemDaoMock.findAll()).thenReturn(mutableListOf(movieItem))

        val movieHttpUtil = mock(MovieHttpUtil::class.java)

        val movieRepository = MovieRepository(movieItemDaoMock)
        movieRepository.getWeeklyMovie { weeklyMovies ->
            Assert.assertEquals(1, weeklyMovies.size)
            Assert.assertEquals(movieItem.movieId, weeklyMovies[0].subject.id)
        }

        verify(movieHttpUtil, times(0)).getWeeklyMovies({}, {})

    }

    @Test
    fun get_weekly_movie_from_http() {
        val application = mock(MyApplication::class.java)
        MyApplication._context = application

        val movieItemDaoMock = mock(MovieItemDao::class.java)
        `when`(movieItemDaoMock.findAll()).thenReturn(mutableListOf())

        val movieRepository = MovieRepository(movieItemDaoMock)
        val latch = CountDownLatch(2)
        movieRepository.getWeeklyMovie { weeklyMovies ->
            Assert.assertNotNull(weeklyMovies.size)
            latch.countDown()
        }
        latch.await()
        verify(movieItemDaoMock, times(1)).insert(ArgumentMatchers.anyList())
    }

    @Test
    fun get_movie_detail() {
        val movieItemDaoMock = mock(MovieItemDao::class.java)
        val movieRepository = MovieRepository(movieItemDaoMock)
        val latch = CountDownLatch(1)
        movieRepository.getMovieDetail("30310218") { movieDetail ->
            Assert.assertNotNull(movieDetail)
            latch.countDown()
        }
        latch.await()
    }

    private fun buildMovieItem() : MovieItem {
        return MovieItem(
            movieId = "30310218",
            delta = 1,
            rank = 1,
            casts = Gson().toJson(mutableListOf<People>()),
            directors = Gson().toJson(mutableListOf<People>()),
            genres = mutableListOf("喜剧", "温馨").joinToString(","),
            originalTitle = "标准之外",
            rating = "{\n" +
                    "                    \"max\": 10,\n" +
                    "                    \"average\": 8.3,\n" +
                    "                    \"details\": {\n" +
                    "                        \"1\": 14.0,\n" +
                    "                        \"3\": 464.0,\n" +
                    "                        \"2\": 34.0,\n" +
                    "                        \"5\": 1203.0,\n" +
                    "                        \"4\": 2190.0\n" +
                    "                    },\n" +
                    "                    \"stars\": \"45\",\n" +
                    "                    \"min\": 0\n" +
                    "                }",
            title = "标准之外",
            year = "2019",
            photos = null,
            images = "{\n" +
                    "                    \"small\": \"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2556765966.jpg\",\n" +
                    "                    \"large\": \"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2556765966.jpg\",\n" +
                    "                    \"medium\": \"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2556765966.jpg\"\n" +
                    "                }"
        )
    }
}