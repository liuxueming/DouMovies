package com.thoughtworks.doumovies

import android.R.attr.data
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.thoughtworks.doumovies.databinding.MovieDetailBinding
import com.thoughtworks.doumovies.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.movie_detail.*
import kotlinx.android.synthetic.main.tool_bar.*
import androidx.lifecycle.Observer
import com.thoughtworks.doumovies.repository.MovieRepository
import com.thoughtworks.doumovies.repository.room.config.DbInstance

class MovieDetailFragment : Fragment() {

    private lateinit var appCompatActivity: AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appCompatActivity = activity as AppCompatActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val movieDetailDataBinding = DataBindingUtil.inflate<MovieDetailBinding>(
            inflater, R.layout.movie_detail, container, false
        )
        val view: View = movieDetailDataBinding.root

        val movieViewModel = MovieViewModel(appCompatActivity.application, MovieRepository(
            DbInstance.getMovieItemDao()))
        movieViewModel.movieDetailLiveData.observe(this, Observer { movieDetail ->
            movieDetailDataBinding.detail = movieDetail
        })
        arguments?.let {
            val movieId = it.getString("movieId") as String
            movieViewModel.getMovieDetail(movieId)
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initToolBarTitle()
    }

    private fun initToolBarTitle() {
        toolbar_scroll_title.visibility = View.GONE

        scroll_view.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            run {
                if (scrollY == 0) {
                    toolbar_origin_title.visibility = View.VISIBLE
                    toolbar_scroll_title.visibility = View.GONE
                } else {
                    toolbar_origin_title.visibility = View.GONE
                    toolbar_scroll_title.visibility = View.VISIBLE
                }
            }
        })

        my_toolbar.setNavigationOnClickListener {
            arguments?.let {
                val rankFragment = it.getSerializable("rankFragment") as MovieRankFragment
                switchFragment(rankFragment)
            }
        }
    }

    private fun switchFragment(targetFragment: Fragment) {
        val transaction = appCompatActivity.supportFragmentManager.beginTransaction()
        if (targetFragment.isAdded) {
            transaction
                .remove(this)
                .show(targetFragment)
                .commit()
        } else {
            transaction
                .remove(this)
                .add(R.id.fragment_frame, targetFragment)
                .commit()
        }
    }
}