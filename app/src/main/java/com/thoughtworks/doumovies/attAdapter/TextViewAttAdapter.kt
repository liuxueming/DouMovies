package com.thoughtworks.doumovies.attAdapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.thoughtworks.doumovies.R

object TextViewAttAdapter {
    @JvmStatic
    @BindingAdapter("ranking")
    fun configRanking(textView: TextView, ranking: Int) {
        textView.text = ranking.toString()
        val resId = when (ranking) {
            1 -> R.drawable.ic_ranking_first
            2 -> R.drawable.ic_ranking_second
            3 -> R.drawable.ic_ranking_third
            else -> R.drawable.ic_ranking_others
        }
        textView.setBackgroundResource(resId)
    }
}