package com.thoughtworks.doumovies

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.movie_detail.*
import kotlinx.android.synthetic.main.tool_bar.*


class MovieDetailFragment : Fragment() {

    lateinit var appCompatActivity: AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appCompatActivity = activity as AppCompatActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.movie_detail, null)
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