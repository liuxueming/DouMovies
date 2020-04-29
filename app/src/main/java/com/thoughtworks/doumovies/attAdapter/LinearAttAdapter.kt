package com.thoughtworks.doumovies.attAdapter

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import com.thoughtworks.doumovies.R
import kotlinx.android.synthetic.main.weekly_rank_item_tags.view.*

object CommentsAtrrAdapter {
    @JvmStatic
    @BindingAdapter("tags")
    fun setTags(parent: LinearLayout, tags: List<String>?) {
        parent.removeAllViews()
        tags?.forEach {
            val tagsView = View.inflate(parent.context, R.layout.weekly_rank_item_tags, null)
            tagsView.tags_text.text = it
            parent.addView(tagsView)
        }
    }
}