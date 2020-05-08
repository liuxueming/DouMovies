package com.thoughtworks.doumovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thoughtworks.doumovies.repository.MovieRepository
import com.thoughtworks.doumovies.repository.room.config.DbInstance
import com.thoughtworks.doumovies.viewmodel.MovieViewModel

class MovieRankFragment : Fragment() {
    private val adapter by lazy { WeeklyRankAdapter() }

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
        val view = inflater.inflate(R.layout.movie_rank_fragement, null)
        val rec = view.findViewById<RecyclerView>(R.id.weekly_rank_recycle_view)
        configWeeklyRankView(rec)
        return view
    }

    private fun configWeeklyRankView(rec: RecyclerView) {
        val movieViewModel = MovieViewModel(
            appCompatActivity.application,
            MovieRepository(DbInstance.getMovieItemDao())
        )
        movieViewModel.weeklyMovieLiveData.observe(this, Observer { weeklyMovieItem ->
            adapter.updateData(weeklyMovieItem)
        })

        movieViewModel.getWeeklyMovie()

        rec.layoutManager = LinearLayoutManager(this.context)
        rec.adapter = adapter

        adapter.setOnItemClickListener(object : WeeklyRankAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int, movieId: String) {
                val bundle = Bundle()
                bundle.putString("movieId", movieId)
                val movieDetailFragment = MovieDetailFragment()
                movieDetailFragment.arguments = bundle
                switchToMovieDetail(movieDetailFragment)
            }
        })
    }

    private fun switchToMovieDetail(targetFragment: Fragment) {
        val transaction = appCompatActivity.supportFragmentManager.beginTransaction()
        transaction
            .hide(this)
            .add(R.id.fragment_frame, targetFragment)
            .addToBackStack(null)
            .commit()
    }
}