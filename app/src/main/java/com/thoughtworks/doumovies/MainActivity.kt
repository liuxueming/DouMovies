package com.thoughtworks.doumovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.thoughtworks.doumovies.viewmodel.MovieViewModel
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
            switchFragment(movieDetailFragment)
        }
    }

    private fun configWeeklyRankView() {
        val bundle = Bundle()
        bundle.putSerializable("adapter",adapter)
        val rankFragment = MovieRankFragment()
        rankFragment.arguments = bundle
        switchFragment(rankFragment)

        adapter?.setOnItemClickListener(object: WeeklyRankAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                Log.d("click","点击了${position} 个")
                //TODO: switch to target fragment
            }
        })
    }

    private fun switchFragment(targetFragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction
            .add(R.id.fragment_frame, targetFragment)
            .commit()
    }
}
