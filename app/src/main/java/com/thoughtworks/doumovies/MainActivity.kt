package com.thoughtworks.doumovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.thoughtworks.doumovies.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.weekly_rank_item.*

class MainActivity : AppCompatActivity() {
    private val adapter by lazy { WeeklyRankAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configWeeklyRankView()

        val movieViewModel = MovieViewModel(this.application)
        movieViewModel.weeklyMovieLiveData.observe(this, Observer { weeklyMovieItem ->
            adapter.updateData(weeklyMovieItem)
        })
        movieViewModel.getWeeklyMovie()


        weekly_rank_cover_img?.setOnClickListener {
            val movieDetailFragment = MovieDetailFragment()
            switchToMovieDetail(movieDetailFragment)
        }
    }

    private fun configWeeklyRankView() {
        weekly_rank_recycle_view.layoutManager = LinearLayoutManager(this)
        weekly_rank_recycle_view.adapter = adapter
    }

    private fun switchToMovieDetail(targetFragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction
            .add(R.id.fragment_frame, targetFragment)
            .commit()
    }
}
