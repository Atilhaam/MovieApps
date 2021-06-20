package com.ilham.movieapplication.core.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ilham.movieapplication.core.data.source.local.entity.MovieEntity
import com.ilham.movieapplication.core.databinding.ItemsMovieBinding
import com.ilham.movieapplication.core.domain.model.Movies

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var listMovies = ArrayList<Movies>()
    var onItemClick: ((Movies)-> Unit)? = null

    fun setMovies(movies: List<Movies>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    inner class MovieViewHolder(private val binding: ItemsMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movies: Movies) {
            with(binding) {
                tvItemTitle.text = movies.title
                tvItemDescription.text = movies.description
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/original/${movies.poster}")
                    .into(imgPoster)
            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listMovies[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsMovieBinding = ItemsMovieBinding.inflate(LayoutInflater.from(parent.context))
        return MovieViewHolder(itemsMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }
}