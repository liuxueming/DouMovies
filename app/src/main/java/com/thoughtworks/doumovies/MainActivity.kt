package com.thoughtworks.doumovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rankFragment = MovieRankFragment()
        switchFragment(rankFragment, null)
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

    private fun switchFragment(targetFragment: Fragment, currentFragment: Fragment?) {
        val transaction = supportFragmentManager.beginTransaction()
        currentFragment?.let {
            transaction.hide(currentFragment)
        }
        if (targetFragment.isAdded) {
            transaction
                .show(targetFragment)
                .addToBackStack(null)
                .commit()
        } else {
            transaction
                .add(R.id.fragment_frame, targetFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}
