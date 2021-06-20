package com.ilham.movieapplication.detail

import androidx.lifecycle.ViewModel
import com.ilham.movieapplication.core.domain.model.TvShow
import com.ilham.movieapplication.core.domain.usecase.TvShowUseCase

class DetailTvViewModel(private val tvShowUseCase: TvShowUseCase) : ViewModel() {
    fun setFavoriteTvShow(tv: TvShow, newStatus: Boolean) = tvShowUseCase.setTvShowLiked(tv, newStatus)
}