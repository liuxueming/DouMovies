package com.thoughtworks.doumovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable

class MovieRankFragment() : Fragment(), Serializable {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.movie_rank_fragement, null)
        val rec = view.findViewById<RecyclerView>(R.id.weekly_rank_recycle_view)
        rec.layoutManager = LinearLayoutManager(this.context)
        arguments?.let {
            val adapter = it.getSerializable("adapter") as WeeklyRankAdapter
            rec.adapter = adapter
        }
        return view
    }
}