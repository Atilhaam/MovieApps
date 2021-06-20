package com.ilham.movieapplication.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ilham.movieapplication.core.databinding.ItemsMovieBinding
import com.ilham.movieapplication.core.domain.model.TvShow

class TvFavoriteAdapter : RecyclerView.Adapter<TvFavoriteAdapter.TvViewHolder>(){
    private var listTv = ArrayList<TvShow>()
    var onItemClick: ((TvShow) -> Unit)? = null

    fun setTvShow(tv: List<TvShow>?) {
        if (tv == null) return
        this.listTv.clear()
        this.listTv.addAll(tv)
    }
    inner class TvViewHolder(private val binding: ItemsMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tv: TvShow) {
            with(binding) {
                tvItemTitle.text = tv.title
                tvItemDescription.text = tv.description

                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/original/${tv.poster}")
                    .into(imgPoster)
            }

        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listTv[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val itemTvBinding = ItemsMovieBinding.inflate(LayoutInflater.from(parent.context))
        return TvViewHolder(itemTvBinding)
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        val tv = listTv[position]
        holder.bind(tv)
    }

    override fun getItemCount(): Int {
        return listTv.size
    }
}