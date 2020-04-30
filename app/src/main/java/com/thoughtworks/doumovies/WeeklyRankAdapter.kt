package com.thoughtworks.doumovies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.thoughtworks.doumovies.databinding.WeeklyRankItemBinding
import com.thoughtworks.doumovies.model.http.WeeklyMovieItem
import java.io.Serializable

class WeeklyRankAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Serializable {
    private val weeklyMovieItems = mutableListOf<WeeklyMovieItem>()
    private lateinit var onItemClickListener: OnItemClickListener

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WeeklyRankViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.weekly_rank_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return weeklyMovieItems.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as WeeklyRankViewHolder).bind(weeklyMovieItems[position])
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(holder.itemView, position)
        }
    }

    fun updateData(newData: List<WeeklyMovieItem>?) {
        newData?.let {
            weeklyMovieItems.clear()
            weeklyMovieItems.addAll(it)
            notifyDataSetChanged()
        }
    }

    inner class WeeklyRankViewHolder(private val dataBinding: WeeklyRankItemBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {
        fun bind(item: WeeklyMovieItem) {
            dataBinding.item = item
        }
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }
}