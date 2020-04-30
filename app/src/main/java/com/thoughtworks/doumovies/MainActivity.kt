package com.thoughtworks.doumovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.thoughtworks.doumovies.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity() {
    val adapter by lazy { WeeklyRankAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configWeeklyRankView()

        val movieViewModel = MovieViewModel(this.application)
        movieViewModel.weeklyMovieLiveData.observe(this, Observer { weeklyMovieItem ->
            adapter.updateData(weeklyMovieItem)
        })
        movieViewModel.getWeeklyMovie()
    }

    override fun onBackPressed() {
        val fragments = supportFragmentManager.fragments
        val rankFragments = fragments.filterIsInstance<MovieRankFragment>()
        val detailFragments = fragments.filterIsInstance<MovieDetailFragment>()
        if (detailFragments.isNotEmpty() && detailFragments[0].isVisible) {
            if (rankFragments.isNotEmpty()) {
                switchFragment(rankFragments[0], detailFragments[0])
            }
        } else {
            super.onBackPressed()
        }
    }

    private fun configWeeklyRankView() {
        val bundle = Bundle()
        bundle.putSerializable("adapter",adapter)
        val rankFragment = MovieRankFragment()
        rankFragment.arguments = bundle
        switchFragment(rankFragment, null)

        adapter.setOnItemClickListener(object: WeeklyRankAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                Log.d("click","点击了${position} 个")
                //TODO: switch to target fragment
                val bundle = Bundle()
                bundle.putSerializable("rankFragment", rankFragment)
                val movieDetailFragment = MovieDetailFragment()
                movieDetailFragment.arguments = bundle
                switchFragment(movieDetailFragment, rankFragment)
            }
        })
    }

    private fun switchFragment(targetFragment: Fragment, currentFragment: Fragment?) {
        val transaction = supportFragmentManager.beginTransaction()
        currentFragment?.let {
            transaction.hide(currentFragment)
        }
        if (targetFragment.isAdded) {
            transaction
                .show(targetFragment)
                .commit()
        } else {
            transaction
                .add(R.id.fragment_frame, targetFragment)
                .commit()
        }
    }
}
