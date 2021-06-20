package com.ilham.movieapplication.detail

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ilham.movieapplication.core.domain.model.TvShow
import com.ilham.movieapplication.databinding.ActivityDetailTvBinding
import com.ilham.movieapplication.databinding.ContentDetailTvBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailTvActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_TV = "extra_tv"
    }

    private lateinit var binding: ActivityDetailTvBinding
    private lateinit var detailContentBinding : ContentDetailTvBinding

    private val detailTvViewModel: DetailTvViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTvBinding.inflate(layoutInflater)
        detailContentBinding = binding.detailContent

        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val detailTvShow = intent.getParcelableExtra<TvShow>(EXTRA_TV)
        showDetailTvShow(detailTvShow)



    }
    private fun showDetailTvShow(detailTvShow: TvShow?) {
        detailTvShow?.let {
            supportActionBar?.title = detailTvShow.title
            detailContentBinding.textTitle.text = detailTvShow.title
            detailContentBinding.textDate.text = detailTvShow.releaseDate
            detailContentBinding.textDescription.text = detailTvShow.description
            detailContentBinding.textDirector.text = detailTvShow.language


            Glide.with(this@DetailTvActivity)
                .load("https://image.tmdb.org/t/p/original/${detailTvShow.poster}")
                .into(detailContentBinding.imagePoster)

            var statusFavorite = detailTvShow.liked
            setStatusFavorite(statusFavorite)
            detailContentBinding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailTvViewModel.setFavoriteTvShow(detailTvShow, statusFavorite)
                setStatusFavorite(statusFavorite)
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