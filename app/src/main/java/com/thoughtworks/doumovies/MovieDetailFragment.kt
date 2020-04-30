package com.thoughtworks.doumovies

import android.os.Bundle
import android.view.*
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.movie_detail.*
import kotlinx.android.synthetic.main.tool_bar.*


class MovieDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.movie_detail, null)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        initToolBarTitle()
        super.onActivityCreated(savedInstanceState)
    }

    private fun initToolBarTitle() {
        toolbar_scroll_title.visibility = View.GONE

        scroll_view.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY -> run {
            if (scrollY == 0) {
                toolbar_origin_title.visibility = View.VISIBLE
                toolbar_scroll_title.visibility = View.GONE
            } else {
                toolbar_origin_title.visibility = View.GONE
                toolbar_scroll_title.visibility = View.VISIBLE
            }
        }})
    }
}