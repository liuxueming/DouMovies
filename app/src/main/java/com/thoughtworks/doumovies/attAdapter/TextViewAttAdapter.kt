package com.thoughtworks.doumovies.attAdapter

import android.graphics.drawable.Drawable
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.thoughtworks.doumovies.R
import com.thoughtworks.doumovies.model.WeeklyMovieItem

object TextViewAttAdapter {

    @JvmStatic
    @BindingAdapter("positiveRate")
    fun configPositiveRate(textView: TextView, positiveRate: Double?) {
        val outputStr =
            StringBuilder(String.format("%.0f", positiveRate?.times(100))).append("%").append("好评")
        textView.text = outputStr.toString()
    }

    @JvmStatic
    @BindingAdapter("intro")
    fun configIntro(textView: TextView, item: WeeklyMovieItem) {
        val outputStr = StringBuilder(item.year).append(" / ")
        item.countries?.forEach {
            outputStr.append(it)
            outputStr.append(" / ")
        }
        item.genres.forEach {
            outputStr.append(it)
            outputStr.append(" ")
        }
        outputStr.append("/ ")
        item.directors.forEach {
            outputStr.append(it.name)
            outputStr.append(" ")
        }
        outputStr.append("/ ")
        item.casts.forEach {
            outputStr.append(it.name)
            outputStr.append(" ")
        }
        textView.text = outputStr.toString()
    }

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