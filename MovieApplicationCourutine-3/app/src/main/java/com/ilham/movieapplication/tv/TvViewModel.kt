package com.ilham.movieapplication.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ilham.movieapplication.core.domain.usecase.TvShowUseCase

class TvViewModel(tvShowUseCase: TvShowUseCase) : ViewModel() {
    val getTvShows = tvShowUseCase.getAllTvShow().asLiveData()
}