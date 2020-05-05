package com.thoughtworks.doumovies.attAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.thoughtworks.doumovies.R
import com.thoughtworks.doumovies.model.http.People
import com.thoughtworks.doumovies.model.http.Photo
import com.thoughtworks.doumovies.model.http.PopularComment
import kotlinx.android.synthetic.main.cast_card.view.*
import kotlinx.android.synthetic.main.comment_card.view.*
import kotlinx.android.synthetic.main.detail_tags.view.*
import kotlinx.android.synthetic.main.photos_item.view.*
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

    @JvmStatic
    @BindingAdapter("detail_tags")
    fun setDetailTags(parent: LinearLayout, tags: List<String>?) {
        tags?.forEach {
            val tagView = LayoutInflater.from(parent.context).inflate(R.layout.detail_tags, parent, false)
            tagView.tag_text.text = it
            parent.addView(tagView)
        }
    }

    @JvmStatic
    @BindingAdapter("cast_directors")
    fun setCastAndDirectors(parent: ViewGroup, castAndDirectors: List<People>?) {
        castAndDirectors?.forEach {
            val tagView = LayoutInflater.from(parent.context).inflate(R.layout.cast_card, parent, false)
            Glide.with(parent.context)
                .applyDefaultRequestOptions(RequestOptions().placeholder(R.drawable.ic_image_loading))
                .load(it.avatars.small).into(tagView.cast_img)
            tagView.cast_name.text= it.name
            tagView.cast_role.text= it.role
            parent.addView(tagView)
        }
    }

    @JvmStatic
    @BindingAdapter("photos")
    fun setPhotos(parent: ViewGroup, castAndDirectors: List<Photo>?) {
        castAndDirectors?.forEach {
            val photoView = LayoutInflater.from(parent.context).inflate(R.layout.photos_item, parent, false)
            Glide.with(parent.context)
                .applyDefaultRequestOptions(RequestOptions().placeholder(R.drawable.ic_image_loading))
                .load(it.image).into(photoView.photo_img)
            parent.addView(photoView)
        }
    }

    @JvmStatic
    @BindingAdapter("comments")
    fun setComments(parent: ViewGroup, comments: List<PopularComment>?) {
        comments?.forEach {
            val commentView = LayoutInflater.from(parent.context).inflate(R.layout.comment_card, parent, false)
            Glide.with(parent.context)
                .applyDefaultRequestOptions(RequestOptions().placeholder(R.drawable.ic_image_loading))
                .load(it.author.avatar).into(commentView.commentator_img)
            commentView.commentator_nick.text = it.author.name
            commentView.create_time.text = it.created_at
            commentView.commentator_content.text = it.content
            commentView.commentator_stars.rating = it.rating.value.toFloat()
            parent.addView(commentView)
        }
    }
}