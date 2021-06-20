package com.ilham.movieapplication.favorite.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ilham.movieapplication.core.domain.usecase.TvShowUseCase

class TvFavoriteViewModel(private val tvShowUseCase: TvShowUseCase) : ViewModel() {
    val favoriteTvShow = tvShowUseCase.getLikedTvShow().asLiveData()
}