package com.thoughtworks.doumovies.attAdapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
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
}