package com.thoughtworks.doumovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.thoughtworks.doumovies.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //测试
        val movieViewModel = MovieViewModel(this.application)
        movieViewModel.weeklyMovieLiveData.observe(this, Observer { weeklyMovie -> println(weeklyMovie.title) })
        movieViewModel.getWeeklyMovie()
    }
}
