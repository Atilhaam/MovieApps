package com.ilham.movieapplication.detail

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ilham.movieapplication.core.domain.model.Movies
import com.ilham.movieapplication.databinding.ActivityDetailMoviesBinding
import com.ilham.movieapplication.databinding.ContentDetailMovieBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    private lateinit var binding: ActivityDetailMoviesBinding
    private lateinit var detailContentBinding: ContentDetailMovieBinding

    private val detailMoviesViewModel: DetailMovieViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailMoviesBinding.inflate(layoutInflater)
        detailContentBinding = binding.detailContent

        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val detailMovies = intent.getParcelableExtra<Movies>(EXTRA_MOVIE)
        showDetailMovies(detailMovies)
    }
    private fun showDetailMovies(detailMovies: Movies?) {
        detailMovies?.let {
            supportActionBar?.title = detailMovies.title
            detailContentBinding.textTitle.text = detailMovies.title
            detailContentBinding.textDate.text = detailMovies.releaseDate
            detailContentBinding.textDescription.text = detailMovies.description
            detailContentBinding.textDirector.text = detailMovies.language

            Glide.with(this)
                .load("https://image.tmdb.org/t/p/original/${detailMovies.poster}")
                .into(detailContentBinding.imagePoster)

            var statusFavorite = detailMovies.liked
            println("$statusFavorite")
            setStatusFavorite(statusFavorite)
            detailContentBinding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailMoviesViewModel.setFavoriteMovie(detailMovies, statusFavorite)
                setStatusFavorite(statusFavorite)
                println("$statusFavorite")
            }
        }

    }


    private fun setStatusFavorite(statusFavorite: Boolean) {
    if (statusFavorite) {
        detailContentBinding.fab.setColorFilter(Color.RED)
    } else {
        detailContentBinding.fab.setColorFilter(Color.WHITE)
    }
    }


}